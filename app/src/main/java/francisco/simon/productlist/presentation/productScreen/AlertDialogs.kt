package francisco.simon.productlist.presentation.productScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import francisco.simon.productlist.R
import francisco.simon.productlist.ui.theme.DarkBlue

@Composable
fun DeleteAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {

    AlertDialog(
        containerColor = Color.White,
        icon = {
            Icon(
                Icons.Filled.Warning,
                contentDescription = stringResource(R.string.delete_alert_dialog_contentDescription),
                tint = Color.Black
            )
        },
        title = {
            Text(
                text = stringResource(R.string.deleteProductAlert)
            )
        },
        text = {
            Text(text = stringResource(R.string.DeleteProductAlertText))
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(
                    color = DarkBlue,
                    text = stringResource(R.string.YesAlertDialog)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(
                    color = DarkBlue,
                    text = stringResource(R.string.NoAlertDialog)
                )
            }
        }
    )
}