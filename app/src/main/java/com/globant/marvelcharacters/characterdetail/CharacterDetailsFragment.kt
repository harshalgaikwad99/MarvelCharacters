package com.globant.marvelcharacters.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.globant.marvelcharacters.MarvelApplication
import com.globant.marvelcharacters.R
import com.globant.marvelcharacters.characterdetail.viewModel.CharacterDetailViewModel
import com.globant.marvelcharacters.databinding.FragmentCharacterDetailsBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CharacterDetailsFragment : Fragment() {

    @Inject
    lateinit var characterDetailViewModel: CharacterDetailViewModel

    private lateinit var binding: FragmentCharacterDetailsBinding

    private val args: CharacterDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as MarvelApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_character_details, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = characterDetailViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterDetailViewModel.getCharacterDetail(args.characterId)
    }

    companion object {

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadCharacterImage(imageView: ImageView, url: String?) {
            Picasso.with(imageView.context).load(url).into(imageView)
        }
    }

}