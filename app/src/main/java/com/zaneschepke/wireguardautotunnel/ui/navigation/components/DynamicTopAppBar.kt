package com.zaneschepke.wireguardautotunnel.ui.navigation.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zaneschepke.wireguardautotunnel.ui.state.NavBarState
import com.zaneschepke.wireguardautotunnel.ui.theme.LockedDownBannerHeight
import com.zaneschepke.wireguardautotunnel.ui.theme.Black


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicTopAppBar(
    navBarState: NavBarState,
    onMenuClick: (() -> Unit)? = null,
    onPremiumClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = navBarState.showTop,
        enter = slideInVertically() + fadeIn(),
        exit = slideOutVertically() + fadeOut()
    ) {
        Row(
            modifier = modifier
                .padding(top = LockedDownBannerHeight)
                .fillMaxWidth()
                .height(64.dp)
                .background(Black)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left Menu Icon → opens Settings
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        navBarState.onMenuClick?.invoke()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Apps,
                    contentDescription = "Menu",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            // Center Title
            Text(
                text = "Planet proxy",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            // Right Premium Button → opens Premium
            Row(
                modifier = Modifier
                    .background(Color(0xFF2E2E3A), RoundedCornerShape(50))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            navBarState.onPremiumClick?.invoke()
                            println("Premium button clicked")
                        }
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = "Premium",
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Premium",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
