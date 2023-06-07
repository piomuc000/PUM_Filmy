package pl.edu.pbs.pum_filmy.screens.detals

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import pl.edu.pbs.pum_filmy.model.Movie
import pl.edu.pbs.pum_filmy.model.getMovies
import pl.edu.pbs.pum_filmy.widgets.MovieRow

@Composable
fun DetailsScreen(navController: NavController, movieId: Int?) {
    val newmovieList = remember { getMovies().filter { movie -> movie.id == movieId } }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable {
                                navController.popBackStack()
                            }
                    )
                },
                title = { Text(text = "Movies") },
                backgroundColor = androidx.compose.ui.graphics.Color.Transparent,
                elevation = 5.dp
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(it)
        ) {
            MovieRow(movie = newmovieList.first())
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Text(text = "Movie Images")
            HorizontalScrollableImageView(newmovieList)
        }
    }
}

@Composable
private fun HorizontalScrollableImageView(newmovieList: List<Movie>) {
    LazyRow {
        items(newmovieList[0].images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 5.dp
            ) {
                val painter = rememberAsyncImagePainter(image)
                val state = painter.state
                Image(
                    painter = painter,
                    contentDescription = "Movie Poster"
                )
            }
        }
    }
}
