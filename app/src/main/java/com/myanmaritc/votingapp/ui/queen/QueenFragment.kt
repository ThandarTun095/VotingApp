package com.myanmaritc.votingapp.ui.queen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.myanmaritc.votingapp.R
import com.myanmaritc.votingapp.model.KingQueenItem
import com.myanmaritc.votingapp.ui.adapter.KingQueenAdapter
import kotlinx.android.synthetic.main.fragment_queen.*

class QueenFragment : Fragment() ,KingQueenAdapter.OnClickListener{

    private lateinit var queenViewModel: QueenViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        queenViewModel =
            ViewModelProvider(this).get(QueenViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_queen, container, false)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var queenAdapter = KingQueenAdapter()

        recyclerQueen.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = queenAdapter
        }
        queenAdapter.setOnClickListener(this)  //

        queenViewModel.getQueen().observe(viewLifecycleOwner, Observer {queen ->
            queenAdapter.addKingQueen(queen.dataList as List<KingQueenItem>)
        })

    }

    override fun onResume() {
        super.onResume()
        queenViewModel.loadData()
    }

    override fun onClick(item: KingQueenItem) {

        val direction = QueenFragmentDirections.actionNavigationQueenToDetailFragment(item)
        view?.findNavController()?.navigate(direction)  //go to next page
    }
}