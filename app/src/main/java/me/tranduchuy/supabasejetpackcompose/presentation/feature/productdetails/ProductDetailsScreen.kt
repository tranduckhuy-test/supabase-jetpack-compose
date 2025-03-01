package me.tranduchuy.supabasejetpackcompose.presentation.feature.productdetails

import android.content.ContentResolver
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlinx.coroutines.launch
import me.tranduchuy.supabasejetpackcompose.R
import me.tranduchuy.supabasejetpackcompose.presentation.navigation.ProductListDestination
import java.io.ByteArrayOutputStream
import java.io.InputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    navController: NavController,
    productId: String?,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(R.string.product_details_text_screen_title),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
        }
    ) { innerPadding ->
        val name = viewModel.name.collectAsState(initial = "")
        val price = viewModel.price.collectAsState(initial = 0.0)
        val imageUrl = Uri.parse(viewModel.imageUrl.collectAsState(initial = "").value)
        val contentResolver = LocalContext.current.contentResolver

        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            val galleryLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.GetContent())
                { uri ->
                    uri?.let {
                        if (it.toString() != imageUrl.toString()) {
                            viewModel.onImageChange(it.toString())
                        }
                    }
                }

            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error_image)
                    .build()
            )

            Image(
                painter = painter,
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )
            IconButton(
                modifier = modifier.align(alignment = Alignment.CenterHorizontally),
                onClick = { galleryLauncher.launch("image/*") }
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            OutlinedTextField(
                label = {
                    Text(
                        text = "Product name",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                maxLines = 2,
                shape = RoundedCornerShape(32),
                modifier = modifier.fillMaxWidth(),
                value = name.value,
                onValueChange = { viewModel.onNameChange(it) },
            )
            Spacer(modifier = modifier.height(12.dp))
            OutlinedTextField(
                label = {
                    Text(
                        text = "Product price",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                maxLines = 2,
                shape = RoundedCornerShape(32),
                modifier = modifier.fillMaxWidth(),
                value = price.value.toString(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { viewModel.onPriceChange(it.toDouble()) },
            )
            Spacer(modifier = modifier.weight(1f))
            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = {
                    if (imageUrl.host?.contains("supabase") == true) {
                        viewModel.onSaveProduct(image = byteArrayOf())
                    } else {
                        val image = uriToByteArray(contentResolver, imageUrl)
                        viewModel.onSaveProduct(image = image)
                    }
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(
                            message = "Product updated successfully!",
                            duration = SnackbarDuration.Short
                        )
                    }
                },
            ) {
                Text(text = "Save changes")
            }
            Spacer(modifier = modifier.height(12.dp))
            OutlinedButton(
                modifier = modifier.fillMaxWidth(),
                onClick = { navController.popBackStack(ProductListDestination.route, false) }

            ) {
                Text(text = "Cancel")
            }
        }
    }
}


private fun getBytes(inputStream: InputStream): ByteArray {
    val byteBuffer = ByteArrayOutputStream()
    val bufferSize = 1024
    val buffer = ByteArray(bufferSize)
    var len = 0
    while (inputStream.read(buffer).also { len = it } != -1) {
        byteBuffer.write(buffer, 0, len)
    }
    return byteBuffer.toByteArray()
}


private fun uriToByteArray(contentResolver: ContentResolver, uri: Uri): ByteArray {
    if (uri == Uri.EMPTY) {
        return byteArrayOf()
    }
    val inputStream = contentResolver.openInputStream(uri)
    if (inputStream != null) {
        return getBytes(inputStream)
    }
    return byteArrayOf()
}
