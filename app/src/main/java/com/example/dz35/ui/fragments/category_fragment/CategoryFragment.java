package com.example.dz35.ui.fragments.category_fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.dz35.adapters.CategoryAdapter;
import com.example.dz35.base.BaseFragment;
import com.example.dz35.databinding.FragmentCategoryBinding;
import com.example.dz35.utils.interfaces.OnCategoryClickListener;
import com.example.dz35.viewmodel.PixabayViewModel;


public class CategoryFragment extends BaseFragment<FragmentCategoryBinding> implements OnCategoryClickListener {
    PixabayViewModel viewModel;
    CategoryAdapter categoryAdapter;


    @Override
    public FragmentCategoryBinding bind() {
        return FragmentCategoryBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PixabayViewModel.class);
        initListeners();
        initObserver();
    }


    private void initListeners() {
        binding.btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateCategoryBottomSheetFragment createCategoryBottomSheetFragment = new CreateCategoryBottomSheetFragment();
                createCategoryBottomSheetFragment.show(requireActivity().getSupportFragmentManager(), "category dialog opened");

            }
        });
    }

    private void initObserver() {
        viewModel.getCategories().observe(getViewLifecycleOwner(), categoryModels -> {
            if (categoryModels != null) {
                categoryAdapter = new CategoryAdapter(this, categoryModels);
                binding.recyclerview.setAdapter(categoryAdapter);
            }


        });
    }


    @Override
    public void onClick(String category) {
        Navigation.findNavController(requireView()).navigate(CategoryFragmentDirections.actionCategoryFragmentToWordsFragment(category));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}