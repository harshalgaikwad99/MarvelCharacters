package com.globant.marvelcharacters.characterlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.globant.marvelcharacters.MarvelApplication
import com.globant.marvelcharacters.R
import com.globant.marvelcharacters.characterlist.viewmodel.CharacterListViewModel
import com.globant.marvelcharacters.databinding.FragmentCharacterListBinding
import javax.inject.Inject

class CharacterListFragment : Fragment() {

    @Inject
    lateinit var characterListViewModel: CharacterListViewModel

    private lateinit var binding: FragmentCharacterListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as MarvelApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_character_list, container, false
        )

        binding.lifecycleOwner = this
        binding.viewModel = characterListViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterListViewModel.subscribeCharacterState().characterList.observe(viewLifecycleOwner, {
            context?.let { context ->
                val characterListAdapter = CharacterListAdapter(
                    it, ::onCharacterSelected
                )
                binding.rvCharacterList.layoutManager = LinearLayoutManager(context)
                binding.rvCharacterList.adapter = characterListAdapter
            }
        })

        characterListViewModel.getCharacterList()
    }

    private fun onCharacterSelected(characterId: String) {
        findNavController().navigate(
            CharacterListFragmentDirections.goToDetailFragment(
                characterId
            )
        )
    }
}