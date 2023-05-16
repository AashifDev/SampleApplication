package com.example.sampleapplication.mainUi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.sampleapplication.adapter.ViewPagerAdapter
import com.example.sampleapplication.databinding.FragmentTabLayoutBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutFragment : Fragment() {

    lateinit var binding: FragmentTabLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTabLayoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

       /*val fragment = arrayListOf<Fragment>(
           PageOneFragment(),
           PageTwoFragment()
       )

       val adapter = ViewPagerAdapter(
           fragment,
           requireActivity().supportFragmentManager,
           lifecycle
       )

       binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tab One"))
       binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tab Two"))

       binding.viewPager.adapter = adapter

       binding.Layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
           override fun onTabSelected(tab: TabLayout.Tab?) {
               if (tab != null){
                   binding.viewPager.currentItem = tab.position
               }
           }
           override fun onTabUnselected(tab: TabLayout.Tab?) {}
           override fun onTabReselected(tab: TabLayout.Tab?) {}

       })

       binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
           override fun onPageSelected(position: Int) {
               super.onPageSelected(position)
               binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
           }
       })*/

        setupViewPager()
        setupTabLayout()
        //onBackPress()
    }

    private fun onBackPress() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentFragment = binding.viewPager.currentItem
                if(currentFragment == 0){
                    activity?.onBackPressed()
                }else{
                    binding.viewPager.currentItem = currentFragment - 1
                }
            }
        })
    }

    private fun setupTabLayout() {
       TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
           tab.text = "Page One"
           tab.text = "Page Two"
       }.attach()
   }

    private fun setupViewPager() {
       val adapter = ViewPagerAdapter(requireActivity(), 2)
       binding.viewPager.adapter = adapter
   }


}
