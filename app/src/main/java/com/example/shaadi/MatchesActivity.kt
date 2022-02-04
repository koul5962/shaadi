package com.example.shaadi

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shaadi.databinding.ActivityMatchesBinding
import com.example.shaadi.models.Matches
import com.example.shaadi.models.Results
import com.example.shaadi.repository.MatchesRepository
import com.example.shaadi.services.room.MatchesDatabase
import com.example.shaadi.utils.InternetConnectivity
import com.example.shaadi.utils.MatchesListAdapter
import com.example.shaadi.utils.State
import com.example.shaadi.viewModels.MatchesViewModel
import com.example.shaadi.viewModels.MatchesViewModelFactory

class MatchesActivity : AppCompatActivity(), MatchesListAdapter.OnStateChangeListener {

    private lateinit var viewModel: MatchesViewModel
    private lateinit var binding: ActivityMatchesBinding
    private lateinit var matches : Matches
    private var isConnected : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.matches.layoutManager = LinearLayoutManager(applicationContext)

        val dao = MatchesDatabase.getInstance(applicationContext).matchesDao()
        val repository = MatchesRepository(dao)
        viewModel = ViewModelProvider(this, MatchesViewModelFactory(repository))[MatchesViewModel::class.java]

        checkNetworkConnection()


    }

    private fun checkNetworkConnection() {
        val cld = InternetConnectivity(application)
        cld.observe(this, Observer {
            isConnected = it
            getMatches()
        })
    }

    private fun getMatches() {
        binding.progress.visibility = View.VISIBLE
        binding.matches.visibility = View.GONE
        viewModel.getMatches(isConnected)
        viewModel.matchesResponse.observe(this, {
            if(it != null) {
                matches = it
                binding.progress.visibility = View.GONE
                binding.matches.visibility = View.VISIBLE
                setDataToUI()
            } else {
                binding.progress.visibility = View.GONE
                binding.noRecord.visibility = View.VISIBLE
            }
        })
    }

    private fun setDataToUI() {
        val adapter = MatchesListAdapter(applicationContext,this, matches.results)
        binding.matches.adapter = adapter
    }

    override fun onChange(position: Int, state: State) {
        matches.results[position].hasAccepted = state == State.ACCEPTED
        // update Local DB
        viewModel.updateMatches(matches)

    }
}