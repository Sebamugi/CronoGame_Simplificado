package com.example.cronogame.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.cronogame.models.Category
import com.example.cronogame.models.InitialData

@Composable
fun CategoryList(
    categories: List<Category>,
    onCategoryClick: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        categories.forEach { category ->
            CategoryCard(
                text = category.name,
                onClick = { onCategoryClick(category.id) },
                backgroundColor = Color(0xffFFC900),
                textColor = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryList() {
    val sampleCategories = InitialData.categories // Utiliza los datos predefinidos de InitialData

    CategoryList(
        categories = sampleCategories,
        onCategoryClick = { categoryId ->
            // Manejamos el clic y mostramos el ID de la categoría seleccionada
            println("Categoría seleccionada: $categoryId")
        }
    )
}