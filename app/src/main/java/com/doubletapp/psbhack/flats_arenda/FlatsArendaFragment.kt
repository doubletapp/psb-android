package com.doubletapp.psbhack.flats_arenda

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.alex66rus.basearchkotlin.base.ExtendedMutableLiveData
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.extensions.replaceFragment
import com.doubletapp.psbhack.extensions.setupActionBar
import com.doubletapp.psbhack.screens.my_flat.MyFlatActivity
import kotlinx.android.synthetic.main.fragment_flats_arenda.*
import okhttp3.internal.notify

class FlatsArendaFragment : Fragment() {

    private var started: Boolean = false
    private val viewModel by lazy { ViewModelProviders.of(this).get(FlatsArendaViewModel::class.java) }

    private val adapter by lazy { FlatsArendaAdapter(::onItemClick, ::onEditClick) }

    private val repo = FlatsRepo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.flats_arenda_menu, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
//        R.id.flats_arenda_edit -> {
//            // TODO: open flat screen
//            true
//        }
//        else -> super.onOptionsItemSelected(item)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_flats_arenda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flats_arenda_toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
        setupActionBar(R.string.flats_arenda_title, true, R.id.flats_arenda_toolbar)
        flats_arenda_list.adapter = adapter
//        viewModel.flats.observe(viewLifecycleOwner, adapter::submitItems)
        flats_arenda_fab.setOnClickListener {
            replaceFragment(FlatsAddFlatFragment.newInstance(null), android.R.id.content, null)
        }
    }

    override fun onResume() {
        super.onResume()
        val flats: List<FlatModel> = repo.getFlats()
        adapter.submitItems(flats)
        started = false
    }

    private fun onItemClick(item: FlatModel) {
        if (!started) {
            context?.let {
                startActivity(MyFlatActivity.getIntent(it, item))
                started = true
            }
        }
    }

    private fun onEditClick(item: FlatModel) {
        replaceFragment(FlatsAddFlatFragment.newInstance(item), android.R.id.content, null)
    }
}