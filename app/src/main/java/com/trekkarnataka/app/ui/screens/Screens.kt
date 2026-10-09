package com.trekkarnataka.app.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.trekkarnataka.app.data.Trek
import com.trekkarnataka.app.ui.TrekViewModel
import com.trekkarnataka.app.ui.theme.*

// ---------- Landing ----------
@Composable
fun LandingScreen(onContinue: () -> Unit) {
    Box(
        Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Dark, Forest, Leaf))),
        contentAlignment = Alignment.Center
    ) {
        Column(Modifier.padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Terrain, null, tint = Color.White, modifier = Modifier.size(96.dp))
            Spacer(Modifier.height(16.dp))
            Text("Trek Karnataka", color = Color.White, fontSize = 34.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.height(12.dp))
            Text(
                "\"The mountains are calling, and I must go.\"\nBreathe green. Climb higher. Live wilder.",
                color = Color.White.copy(alpha = .9f), textAlign = TextAlign.Center, fontSize = 16.sp, lineHeight = 24.sp
            )
            Spacer(Modifier.height(40.dp))
            Button(
                onClick = onContinue, shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Forest),
                modifier = Modifier.fillMaxWidth().height(54.dp)
            ) { Text("Continue", fontWeight = FontWeight.Bold); Spacer(Modifier.width(8.dp)); Icon(Icons.Default.ArrowForward, null) }
        }
    }
}

// ---------- Shared top bar ----------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Bar(title: String, onBack: () -> Unit) = TopAppBar(
    title = { Text(title, fontWeight = FontWeight.Bold) },
    navigationIcon = { IconButton(onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } },
    colors = TopAppBarDefaults.topAppBarColors(containerColor = Forest, titleContentColor = Color.White, navigationIconContentColor = Color.White)
)

// ---------- Districts ----------
@Composable
fun DistrictScreen(vm: TrekViewModel, onBack: () -> Unit, onPick: (String) -> Unit) {
    val s by vm.state.collectAsStateWithLifecycle()
    Scaffold(topBar = { Bar("Choose District", onBack) }) { pad ->
        Column(Modifier.padding(pad).padding(16.dp)) {
            OutlinedTextField(
                value = s.query, onValueChange = vm::onQuery, singleLine = true,
                placeholder = { Text("Search district...") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(s.districts) { d ->
                    val count = vm.treks(d).size
                    Card(
                        onClick = { onPick(d) }, shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Mint), modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Landscape, null, tint = Forest)
                            Spacer(Modifier.width(12.dp))
                            Column(Modifier.weight(1f)) {
                                Text(d, fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
                                Text(if (count > 0) "$count trekking places" else "Coming soon", color = Color.Gray, fontSize = 13.sp)
                            }
                            Icon(Icons.Default.ChevronRight, null, tint = Forest)
                        }
                    }
                }
            }
        }
    }
}

// ---------- Trek list ----------
@Composable
fun TrekListScreen(district: String, treks: List<Trek>, onBack: () -> Unit, onPick: (String) -> Unit) {
    Scaffold(topBar = { Bar("$district Treks", onBack) }) { pad ->
        if (treks.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(pad), contentAlignment = Alignment.Center) { Text("Treks coming soon for $district") }
        } else LazyColumn(Modifier.padding(pad), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(treks) { t ->
                Card(onClick = { onPick(t.id) }, shape = RoundedCornerShape(20.dp), elevation = CardDefaults.cardElevation(4.dp)) {
                    Box {
                        AsyncImage(t.photos.first(), t.name, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxWidth().height(190.dp))
                        Box(Modifier.matchParentSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color(0xCC000000)))))
                        Column(Modifier.align(Alignment.BottomStart).padding(16.dp)) {
                            Text(t.name, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                            Text("${t.elevationM} m  •  ${t.difficulty}  •  ${t.duration}", color = Color.White.copy(.9f), fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }
}

// ---------- Detail ----------
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(t: Trek, onBack: () -> Unit) {
    val uri = LocalUriHandler.current
    Scaffold(topBar = { Bar(t.name, onBack) }) { pad ->
        Column(Modifier.padding(pad).verticalScroll(rememberScrollState())) {
            val pager = rememberPagerState { t.photos.size }
            HorizontalPager(pager) { AsyncImage(t.photos[it], null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxWidth().height(250.dp)) }
            Column(Modifier.padding(20.dp)) {
                Text(t.name, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
                Text(t.district + ", Karnataka", color = Color.Gray)
                Spacer(Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Stat("Height", "${t.elevationM} m", Modifier.weight(1f))
                    Stat("Distance", "${t.distanceKm} km", Modifier.weight(1f))
                    Stat("Level", t.difficulty, Modifier.weight(1f))
                }
                Spacer(Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Stat("Duration", t.duration, Modifier.weight(1f))
                    Stat("Best time", t.bestSeason, Modifier.weight(1f))
                }
                Spacer(Modifier.height(20.dp))
                Text("About", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(t.description, lineHeight = 22.sp, modifier = Modifier.padding(top = 6.dp))
                Spacer(Modifier.height(24.dp))
                Button(
                    onClick = { uri.openUri(t.bookingUrl) }, shape = RoundedCornerShape(50),
                    modifier = Modifier.fillMaxWidth().height(54.dp)
                ) { Icon(Icons.Default.OpenInBrowser, null); Spacer(Modifier.width(8.dp)); Text("Book this trek", fontWeight = FontWeight.Bold) }
            }
        }
    }
}

@Composable
private fun Stat(label: String, value: String, modifier: Modifier) = Column(
    modifier.clip(RoundedCornerShape(14.dp)).background(Mint).padding(12.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(value, fontWeight = FontWeight.Bold, color = Forest, textAlign = TextAlign.Center)
    Text(label, fontSize = 12.sp, color = Color.Gray)
}
