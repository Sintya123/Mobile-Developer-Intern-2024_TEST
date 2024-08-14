package com.example.mobiledevelopmenintern2024.Screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mobiledevelopmenintern2024.databinding.ActivityThirdScreenBinding
import com.example.mobiledevelopmenintern2024.retrofit.Data
import com.example.mobiledevelopmenintern2024.retrofit.OnUserClickListener
import com.example.mobiledevelopmenintern2024.retrofit.RetrofitInstance
import com.example.mobiledevelopmenintern2024.retrofit.UserAdapter
import com.example.mobiledevelopmenintern2024.retrofit.UserApi
import com.example.mobiledevelopmenintern2024.retrofit.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class Thirdscreen : AppCompatActivity(), OnUserClickListener, SwipeRefreshLayout.OnRefreshListener  {
    private val list = ArrayList<Data>()
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: UserAdapter
    private var page = 1
    private var totalPage = 1
    private var isLoading = false
    private lateinit var name: String

    // View Binding instance
    private lateinit var binding: ActivityThirdScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name = intent.getStringExtra("name")!!

        layoutManager = LinearLayoutManager(this)
        binding.swipeRefresh.setOnRefreshListener(this)

        setupRecyclerView()
        getUsers(false)

        binding.rvUsers.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem  = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val total            = adapter.itemCount

                if (!isLoading && page < totalPage) {
                    if (visibleItemCount + pastVisibleItem >= total) {
                        page++
                        getUsers(false)
                    }
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })

        btnBackHandler()
    }

    private fun getUsers(isOnRefresh: Boolean) {
        isLoading = true
        if (!isOnRefresh) binding.progressBar.visibility = View.VISIBLE
        val page = page
        val retro =RetrofitInstance.getRetroData().create(UserApi::class.java)
        retro.getUsers(page).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                totalPage = response?.body()?.total_pages!!
                val listResponse = response.body()?.data
                if (listResponse != null) {
                    adapter.addList(listResponse)
                }

                binding.progressBar.visibility = View.INVISIBLE
                isLoading = false
                binding.swipeRefresh.isRefreshing = false
            }

           override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@Thirdscreen, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvUsers.setHasFixedSize(true)
        binding.rvUsers.layoutManager = layoutManager
        adapter = UserAdapter(list, this@Thirdscreen)
        binding.rvUsers.adapter = adapter
    }

    override fun onRefresh() {
        adapter.clear()
        page = 1
        getUsers(true)
    }

    /*
    * private fun btnBackHandler
    * -> button to go back to SecondScreen with name passed
    * */
    private fun btnBackHandler() {
        // Create intent
        val intent = Intent(this, SecondScreen::class.java)
        intent.putExtra("name", name)

        // Move activity when btn_back clicked
        binding.btnBack.setOnClickListener {
            startActivity(intent)
        }
    }

    override fun onUserItemClicked(position: Int) {
        val intent = Intent(this, SecondScreen::class.java)
        intent.putExtra("name", name)
        intent.putExtra("username", list[position].first_name + " " + list[position].last_name)
        startActivity(intent)
    }
}