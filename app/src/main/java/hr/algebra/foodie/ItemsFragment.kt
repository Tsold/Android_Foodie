package hr.algebra.foodie
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.foodie.adapter.CategoryAdapter
import hr.algebra.foodie.adapter.MealAdapter
import hr.algebra.foodie.frame_work.fetchCategories
import hr.algebra.foodie.frame_work.fetchMeals
import hr.algebra.foodie.model.Category
import hr.algebra.foodie.model.Meal
import kotlinx.android.synthetic.main.fragment_item.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ItemFragment : Fragment() {

    lateinit var categoryList: MutableList<Category>
    lateinit var mealList: MutableList<Meal>

    lateinit var categoryAdapter: CategoryAdapter
    lateinit var mealAdapter: MealAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryList = requireContext().fetchCategories()
        categoryAdapter = CategoryAdapter(categoryList, requireContext(),this)
        rv_main_category.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        rv_main_category.adapter = categoryAdapter
        getMealFromDb(categoryList[0].strCategory)
        }




     fun getMealFromDb(categoryName: String) {
        tvCategory.text = categoryName
        GlobalScope.launch {
            this.let {
                mealList = requireContext().fetchMeals(categoryName)
                mealAdapter = MealAdapter(mealList,requireContext())
                Handler(Looper.getMainLooper()).post {
                    rv_sub_category.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    rv_sub_category.adapter = mealAdapter
                }
            }
        }

    }


}
