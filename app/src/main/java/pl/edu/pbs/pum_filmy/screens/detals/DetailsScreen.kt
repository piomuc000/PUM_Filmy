package pl.edu.pbs.pum_filmy.screens.detals

@Composable
fun DetailsScreen(navController: NavController, movieId: Int?) {
    val newmovieList = getMovies().filter {
        movie -> movie.id == movieId
    }
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Transparent, elevation = 5.dp) {
                Row(horizontalArrangement = Arrangement.Start) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(text = "Movies")
                }
            }
        }
    ) {
        androidx.compose.material.Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                MovieRow(movie = newmovieList.first())
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "Movie Images")
                horizontalScrollableImageView(newmovieList)

            }
        }
    }
}

@Composable
private fun horizontalScrollableImageView(newmovieList: List<Movie>) {
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