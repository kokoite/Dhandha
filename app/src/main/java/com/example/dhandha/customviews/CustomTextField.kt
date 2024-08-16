import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.Icon
import androidx.compose.runtime.remember
import com.example.dhandha.ui.theme.AppTheme

@Composable
fun CustomTextField(
    textState: MutableState<String>,
    placeholderText: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null
) {

    BasicTextField(
        value = textState.value,
        singleLine = true,
        onValueChange = { textState.value = it },
        textStyle = MaterialTheme.typography.bodyMedium,
        modifier = modifier,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp),  // Inner padding and background color
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.padding(end = 8.dp)  // Spacing between icon and text
                    )
                }

                // Text Field
                Box(modifier = Modifier.weight(1f)) {
                    if (textState.value.isEmpty()) {
                        Text(
                            text = placeholderText,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.LightGray
                        )
                    }
                    innerTextField()
                }

                if (trailingIcon != null && !textState.value.isEmpty()) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    )
}

@Composable
fun SearchTextField(textState: MutableState<String>, placeholderText: String, modifier: Modifier) {
    BasicTextField(
        value = textState.value,
        singleLine = true,
        onValueChange = { textState.value = it },
        textStyle = AppTheme.typography.label,
        modifier = modifier,
        decorationBox = { innerTextField ->
            val interactionSource = remember { MutableInteractionSource() }
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp),  // Inner padding and background color
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.padding(end = 8.dp)  // Spacing between icon and text
                )
                // Text Field
                Box(modifier = Modifier.weight(1f)) {
                    if (textState.value.isEmpty()) {
                        Text(
                            text = placeholderText,
                            style = AppTheme.typography.placeholder,
                            color = Color.LightGray
                        )
                    }
                    innerTextField()
                }

                if (!textState.value.isEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.padding(horizontal = 8.dp).clickable(interactionSource, null) {
                            textState.value = ""
                        }
                    )
                }
            }
        }
    )
}
