package francisco.simon.productlist.presentation.productScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import francisco.simon.productlist.R
import francisco.simon.productlist.ui.theme.DarkBlue

@Composable
fun DeleteAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {

    AlertDialog(
        containerColor = Color.White,
        textContentColor = Color.Black,
        titleContentColor = Color.Black,
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

/**
 * This alert dialog increases/decreases amount by 1 when corresponding button pressed
 */
@Composable
fun EditAlertDialog(
    amount: MutableIntState,
    onAmountChange: (Int) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    AlertDialog(
        containerColor = Color.White,
        textContentColor = Color.Black,
        titleContentColor = Color.Black,
        icon = {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = stringResource(R.string.edit_product_alert_dialog_contentDescription),
                tint = Color.Black
            )
        },
        title = {
            Text(
                text = stringResource(R.string.amount_of_product)
            )
        },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {
                        if (amount.intValue > 0) {
                            onAmountChange(amount.intValue - 1)
                        }
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(45.dp),
                        painter = painterResource(id = R.drawable.minus_circle24px),
                        contentDescription = stringResource(R.string.minus_circle_description),
                        tint = DarkBlue
                    )
                }
                Text(
                    text = amount.intValue.toString(),
                    fontSize = 30.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                IconButton(
                    onClick = {
                        onAmountChange(amount.intValue + 1)
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(45.dp),
                        painter = painterResource(id = R.drawable.add_circle_24px),
                        contentDescription = stringResource(R.string.add_circle_description),
                        tint = DarkBlue
                    )
                }
            }
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
                    text = stringResource(R.string.AcceptEdit)
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
                    text = stringResource(R.string.DenyEdit)
                )
            }
        }
    )
}