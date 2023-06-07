package pl.edu.pbs.pum_filmy.widgets


@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val density = LocalDensity.current
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movie.id.toString())
            } ,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .size(Size.ORIGINAL)
                        .transformations(CircleCropTransformation())
                        .crossfade(false)
                        .build()
                )
                Image(painter = painter, contentDescription = "Movie Poster")
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                Text(text = "Released:${movie.year}", style = MaterialTheme.typography.caption)
                AnimatedVisibility(
                    visible = expanded,
                    enter = slideInVertically {
                        with(density) { -40.dp.roundToPx() }
                    } + expandVertically(
                        expandFrom = Alignment.CenterVertically
                    ) + fadeIn(initialAlpha = 0.4f),
                    exit = slideOutVertically() + shrinkVertically() + fadeOut()
                ) {
                    Column {
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 13.sp
                                    )
                                ) {
                                    append("Plot:")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Magenta,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                ) {
                                    append(movie.plot)
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Blue,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                ) {
                                    append("\n  Mobile kurs4")
                                }
                            },
                            modifier = Modifier.padding(6.dp)
                        ) // koniec Text
                        Divider(modifier = Modifier.padding(3.dp))
                        Text(text = "Director:${movie.director}", style = MaterialTheme.typography.caption)
                        Text(text = "Actors:${movie.actors}", style = MaterialTheme.typography.caption)
                    } // koniec column
                }
                Icon(
                    imageVector = if(expanded)Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.LightGray
                )
            }
        }
    }
}