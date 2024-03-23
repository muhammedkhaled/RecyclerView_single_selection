package com.mkhaled.recyclerviewsingleselection

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView


data class MyItem(
    val text: String,
    val image: String = "",
)

class MyAdapter(private val data: List<MyItem>) : RecyclerView.Adapter<MyViewHolder>() {

    private var selectedItemPos = -1
    private var lastItemSelectedPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data[holder.adapterPosition]

        if (position == selectedItemPos)
            holder.selected()
        else
            holder.default()

        holder.bind(item)


        holder.itemView.setOnClickListener {
            selectedItemPos = holder.adapterPosition
            lastItemSelectedPos = if (lastItemSelectedPos == -1)
                selectedItemPos
            else {
                notifyItemChanged(lastItemSelectedPos)
                selectedItemPos
            }
            notifyItemChanged(selectedItemPos)
        }

    }

    override fun getItemCount(): Int = data.size
}


class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textView = itemView.findViewById<TextView>(R.id.textView)
    private val imageView = itemView.findViewById<ImageView>(R.id.image)

    fun bind(item: MyItem) {
        // Update view elements with item data (e.g., text from MyItem)
        textView.text = item.text
    }

    fun default() {
        textView.visibility = View.GONE
        imageView.setPadding(12, 12, 12, 12)
        imageView.translationY = 0f
        imageView.setColorFilter(Color.BLACK)
        val shape = AppCompatResources.getDrawable(itemView.context, R.drawable.circle_shape)
        ViewCompat.setBackground(imageView, shape)
    }

    fun selected() {
        // set image tint
        imageView.setColorFilter(Color.RED)
        textView.visibility = View.VISIBLE
        imageView.setPadding(0, 0, 0, 0)
        imageView.translationY = -18f
        ViewCompat.setBackground(imageView, null)
    }

}
