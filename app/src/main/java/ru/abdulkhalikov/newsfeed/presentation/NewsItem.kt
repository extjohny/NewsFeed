package ru.abdulkhalikov.newsfeed.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import ru.abdulkhalikov.newsfeed.R
import ru.abdulkhalikov.newsfeed.domain.NewsItem
import ru.abdulkhalikov.newsfeed.domain.StatisticItem
import ru.abdulkhalikov.newsfeed.domain.StatisticType

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
                avatarUrl = newsItem.avatarUrl,
                publicationDate = newsItem.publishedAt
            )
            Spacer(modifier = Modifier.height(8.dp))
            NewsItemText(contentText = newsItem.text)
            Spacer(modifier = Modifier.height(8.dp))
            NewsItemImage(imageUrl = newsItem.imageUrl)
            Spacer(modifier = Modifier.height(8.dp))
            NewsItemStatistics(statistics = newsItem.statistics)
        }
    }
}

@Composable
private fun NewsItemHeader(
    author: String,
    avatarUrl: String,
    publicationDate: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            model = avatarUrl,
            contentDescription = null
        )
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
private fun NewsItemStatistics(
    statistics: List<StatisticItem>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            val viewsItem = statistics.getItemByType(StatisticType.VIEWS)
            Statistics(
                text = viewsItem.count.toString(),
                iconResId = R.drawable.ic_views_count
            )
        }
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val sharesItem = statistics.getItemByType(StatisticType.SHARES)
            Statistics(
                text = sharesItem.count.toString(),
                iconResId = R.drawable.ic_share
            )
            val likesItem = statistics.getItemByType(StatisticType.LIKES)
            Statistics(
                text = likesItem.count.toString(),
                iconResId = R.drawable.ic_like
            )
        }
    }
}

@Composable
private fun Statistics(
    text: String,
    iconResId: Int
) {
    Row(
        modifier = Modifier.clickable {

        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(iconResId),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            fontFamily = FontFamily.Default,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun NewsItemImage(
    imageUrl: String?
) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(4f / 3f),
        contentScale = ContentScale.Crop,
        model = imageUrl,
        contentDescription = null,
    )
}

private fun List<StatisticItem>.getItemByType(type: StatisticType): StatisticItem {
    return this.find { it.type == type }
        ?: throw IllegalStateException("Statistic item with type $type not found")
}
