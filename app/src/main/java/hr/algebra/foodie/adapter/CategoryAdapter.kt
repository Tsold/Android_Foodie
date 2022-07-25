package hr.algebra.foodie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.foodie.ItemFragment
import hr.algebra.foodie.R
import hr.algebra.foodie.model.Category
import java.io.File

class CategoryAdapter(private val categoryList: MutableList<Category>, private val context: Context, private val fragment: ItemFragment) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivItem: ImageView = itemView.findViewById(R.id.img_dish)
        private val tvItem: TextView = itemView.findViewById(R.id.tv_dish_name)
        fun bind(item: Category) {
            Picasso.get()
                .load(File(item.strCategoryThumb))
                .error(R.drawable.beef)
                .into(ivItem)
            tvItem.text = item.strCategory
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false))
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener{
            fragment.getMealFromDb(categoryList[position].strCategory)
        }

        holder.bind(categoryList[position])
    }

}