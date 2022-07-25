package hr.algebra.foodie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.foodie.*
import hr.algebra.foodie.frame_work.startActivity
import hr.algebra.foodie.model.Meal
import java.io.File

class MealAdapter(private val mealList: MutableList<Meal>, private val context: Context) : RecyclerView.Adapter<MealAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivItem: ImageView = itemView.findViewById(R.id.img_dish)
        private val tvItem: TextView = itemView.findViewById(R.id.tv_dish_name)
        fun bind(item: Meal) {
            Picasso.get()
                .load(File(item.strMealThumb))
                .error(R.drawable.beef)
                .into(ivItem)
            tvItem.text = item.strMeal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_meal,parent,false))
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener{
            context.startActivity<MealDetailsActivity>(ID,mealList[position].idMeal.toString())
        }

        holder.bind(mealList[position])

    }

}