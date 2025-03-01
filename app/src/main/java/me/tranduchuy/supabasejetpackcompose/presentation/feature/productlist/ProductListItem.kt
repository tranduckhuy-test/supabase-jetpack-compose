package me.tranduchuy.supabasejetpackcompose.presentation.feature.productlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import me.tranduchuy.supabasejetpackcompose.R
import me.tranduchuy.supabasejetpackcompose.domain.model.Product
import me.tranduchuy.supabasejetpackcompose.ui.theme.SupabaseJetpackComposeTheme

@Composable
fun ProductListItem(
    modifier: Modifier = Modifier,
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        Row(
            modifier = modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.image)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_image)
                    .build()
            )

            Image(
                contentDescription = null,
                painter = painter,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .size(64.dp)
            )
            Text(
                text = product.name,
                modifier = modifier.weight(1.0f)
            )
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(name = "ProductListItem Compose")
@Composable
fun ProductListPreview() {
    SupabaseJetpackComposeTheme {

        ProductListItem(product = Product("123", "Hello", 123.3, "123"), onClick = {})
    }
}