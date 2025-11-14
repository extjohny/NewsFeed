package ru.abdulkhalikov.newsfeed.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import ru.abdulkhalikov.newsfeed.domain.NewsItem

@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    newsItem: NewsItem,
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            NewsItemHeader(
                author = newsItem.author,
                publicationDate = newsItem.date
            )
            Spacer(modifier = Modifier.height(8.dp))
            NewsItemText(contentText = newsItem.text)
            Spacer(modifier = Modifier.height(8.dp))
            NewsItemImage(imageUrl = newsItem.imageUrl)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun NewsItemHeader(
    author: String,
    publicationDate: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(1f)
        ) {
            Text(
                text = author,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = publicationDate,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun NewsItemText(
    contentText: String
) {
    Text(
        text = contentText,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 16.sp
    )
}

@Composable
private fun NewsItemImage(
    imageUrl: String?
) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .size(200.dp),
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    )
}