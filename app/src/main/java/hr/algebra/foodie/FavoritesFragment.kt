package hr.algebra.foodie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.foodie.adapter.MealAdapter
import hr.algebra.foodie.frame_work.fetchMealFavorites
import hr.algebra.foodie.model.Meal
import kotlinx.android.synthetic.main.fragment_favorites.*




class FavoritesFragment : Fragment() {

    lateinit var mealList: MutableList<Meal>
    lateinit var mealAdapter: MealAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mealList = requireContext().fetchMealFavorites()
        mealAdapter = MealAdapter(mealList,requireContext())

        rv_fav_meals.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv_fav_meals.adapter = mealAdapter

        btnRefresh.setOnClickListener {
            refreshData()
        }
    }

 private fun refreshData(){

    mealList = requireContext().fetchMealFavorites()
    mealAdapter = MealAdapter(mealList,requireContext())
    rv_fav_meals.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    rv_fav_meals.adapter = mealAdapter


}


}