package com.rentalin.feature.customers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rentalin.core.designsystem.theme.RentalInDimens
import com.rentalin.core.designsystem.theme.RentalInOnPrimary
import com.rentalin.core.designsystem.theme.RentalInOnSurface
import com.rentalin.core.designsystem.theme.RentalInOnSurfaceVariant
import com.rentalin.core.designsystem.theme.RentalInOutlineVariant
import com.rentalin.core.designsystem.theme.RentalInPrimary
import com.rentalin.core.designsystem.theme.RentalInRadii
import com.rentalin.core.designsystem.theme.RentalInTheme
import com.rentalin.core.ui.RentalInAvatar
import com.rentalin.core.ui.RentalInSectionCard
import com.rentalin.core.ui.RentalInStatusChip
import com.rentalin.core.ui.RentalInStatusStyle

@Composable
fun CustomersScreen(
    modifier: Modifier = Modifier,
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    CustomersContent(
        searchQuery = searchQuery,
        onSearchQueryChange = { searchQuery = it },
        modifier = modifier,
    )
}

@Composable
private fun CustomersContent(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val customers = customerItems()
    val filteredCustomers = customers.filter { customer ->
        customer.matches(searchQuery)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = RentalInDimens.ScreenPaddingWide),
        verticalArrangement = Arrangement.spacedBy(RentalInDimens.Sm),
    ) {
        item {
            CustomerSearchAndAddRow(
                searchQuery = searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                modifier = Modifier.padding(top = RentalInDimens.Md),
            )
        }
        items(filteredCustomers) { customer ->
            CustomerRow(customer = customer)
        }
        if (filteredCustomers.isEmpty()) {
            item {
                EmptyCustomersSearch(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = RentalInDimens.Lg),
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(RentalInDimens.Md))
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun CustomersScreenPreview() {
    RentalInTheme {
        CustomersScreen()
    }
}

@Composable
private fun CustomerSearchAndAddRow(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(RentalInDimens.Sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            placeholder = {
                Text(
                    text = stringResource(R.string.customers_search_placeholder),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(RentalInRadii.Medium),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = RentalInPrimary,
                unfocusedBorderColor = RentalInOutlineVariant,
                focusedLeadingIconColor = RentalInPrimary,
                unfocusedLeadingIconColor = RentalInOnSurfaceVariant,
            ),
        )
        Button(
            onClick = {},
            modifier = Modifier.height(56.dp),
            shape = RoundedCornerShape(RentalInRadii.Medium),
            colors = ButtonDefaults.buttonColors(
                containerColor = RentalInPrimary,
                contentColor = RentalInOnPrimary,
            ),
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(
                text = stringResource(R.string.customers_action_add_customer),
                maxLines = 1,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun CustomerSearchAndAddRowPreview() {
    RentalInTheme {
        CustomerSearchAndAddRow(
            searchQuery = "",
            onSearchQueryChange = {},
            modifier = Modifier.padding(RentalInDimens.Md),
        )
    }
}

@Composable
private fun CustomerRow(
    customer: CustomerItem,
    modifier: Modifier = Modifier,
) {
    RentalInSectionCard(modifier = modifier) {
        Row(
            modifier = Modifier.padding(RentalInDimens.Md),
            horizontalArrangement = Arrangement.spacedBy(RentalInDimens.Sm),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RentalInAvatar(initials = customer.initials)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(RentalInDimens.Unit),
            ) {
                Text(
                    text = customer.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = RentalInOnSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = customer.contact,
                    style = MaterialTheme.typography.bodyMedium,
                    color = RentalInOnSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = customer.rentalSummary,
                    style = MaterialTheme.typography.labelMedium,
                    color = RentalInOnSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = customer.dateNote,
                    style = MaterialTheme.typography.bodySmall,
                    color = RentalInOnSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(RentalInDimens.Xs),
            ) {
                Text(
                    text = customer.balance,
                    style = MaterialTheme.typography.titleSmall,
                    color = customer.balanceStyle.balanceColor(),
                    maxLines = 1,
                )
                RentalInStatusChip(
                    label = customer.status,
                    style = customer.statusStyle,
                )
            }
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = RentalInOnSurfaceVariant,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun CustomerRowPreview() {
    RentalInTheme {
        CustomerRow(
            customer = customerItems().first(),
            modifier = Modifier.padding(RentalInDimens.Md),
        )
    }
}

@Composable
private fun EmptyCustomersSearch(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(R.string.customers_empty_search),
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        color = RentalInOnSurfaceVariant,
        textAlign = TextAlign.Center,
    )
}

@Preview(showBackground = true, widthDp = 390)
@Composable
private fun EmptyCustomersSearchPreview() {
    RentalInTheme {
        CustomersContent(
            searchQuery = "No match",
            onSearchQueryChange = {},
        )
    }
}

@Composable
private fun customerItems() = listOf(
    CustomerItem(
        initials = stringResource(R.string.customers_initials_jason),
        name = stringResource(R.string.customers_name_jason),
        contact = stringResource(R.string.customers_phone_jason),
        rentalSummary = stringResource(R.string.customers_rentals_two),
        dateNote = stringResource(R.string.customers_due_may_12),
        balance = stringResource(R.string.customers_balance_zero),
        status = stringResource(R.string.customers_status_active),
        statusStyle = RentalInStatusStyle.Active,
        balanceStyle = CustomerBalanceStyle.Clear,
    ),
    CustomerItem(
        initials = stringResource(R.string.customers_initials_maya),
        name = stringResource(R.string.customers_name_maya),
        contact = stringResource(R.string.customers_email_maya),
        rentalSummary = stringResource(R.string.customers_rentals_one),
        dateNote = stringResource(R.string.customers_due_may_15),
        balance = stringResource(R.string.customers_balance_45),
        status = stringResource(R.string.customers_status_unpaid),
        statusStyle = RentalInStatusStyle.Overdue,
        balanceStyle = CustomerBalanceStyle.Overdue,
    ),
    CustomerItem(
        initials = stringResource(R.string.customers_initials_tyler),
        name = stringResource(R.string.customers_name_tyler),
        contact = stringResource(R.string.customers_phone_tyler),
        rentalSummary = stringResource(R.string.customers_rentals_three),
        dateNote = stringResource(R.string.customers_due_may_10),
        balance = stringResource(R.string.customers_balance_120),
        status = stringResource(R.string.customers_status_due_today),
        statusStyle = RentalInStatusStyle.DueToday,
        balanceStyle = CustomerBalanceStyle.Overdue,
    ),
    CustomerItem(
        initials = stringResource(R.string.customers_initials_leah),
        name = stringResource(R.string.customers_name_leah),
        contact = stringResource(R.string.customers_email_leah),
        rentalSummary = stringResource(R.string.customers_rentals_none),
        dateNote = stringResource(R.string.customers_no_rental_history),
        balance = stringResource(R.string.customers_balance_zero),
        status = stringResource(R.string.customers_status_no_rentals),
        statusStyle = RentalInStatusStyle.Neutral,
        balanceStyle = CustomerBalanceStyle.Clear,
    ),
    CustomerItem(
        initials = stringResource(R.string.customers_initials_andrew),
        name = stringResource(R.string.customers_name_andrew),
        contact = stringResource(R.string.customers_phone_andrew),
        rentalSummary = stringResource(R.string.customers_rentals_one),
        dateNote = stringResource(R.string.customers_due_may_20),
        balance = stringResource(R.string.customers_balance_25),
        status = stringResource(R.string.customers_status_unpaid),
        statusStyle = RentalInStatusStyle.LowStock,
        balanceStyle = CustomerBalanceStyle.Warning,
    ),
    CustomerItem(
        initials = stringResource(R.string.customers_initials_samantha),
        name = stringResource(R.string.customers_name_samantha),
        contact = stringResource(R.string.customers_email_samantha),
        rentalSummary = stringResource(R.string.customers_rentals_two),
        dateNote = stringResource(R.string.customers_due_may_22),
        balance = stringResource(R.string.customers_balance_zero),
        status = stringResource(R.string.customers_status_active),
        statusStyle = RentalInStatusStyle.Active,
        balanceStyle = CustomerBalanceStyle.Clear,
    ),
)

private data class CustomerItem(
    val initials: String,
    val name: String,
    val contact: String,
    val rentalSummary: String,
    val dateNote: String,
    val balance: String,
    val status: String,
    val statusStyle: RentalInStatusStyle,
    val balanceStyle: CustomerBalanceStyle,
) {
    fun matches(query: String): Boolean {
        val normalizedQuery = query.trim().lowercase()
        return normalizedQuery.isEmpty() ||
            name.lowercase().contains(normalizedQuery) ||
            contact.lowercase().contains(normalizedQuery)
    }
}

private enum class CustomerBalanceStyle {
    Clear,
    Warning,
    Overdue,
}

@Composable
private fun CustomerBalanceStyle.balanceColor() = when (this) {
    CustomerBalanceStyle.Clear -> MaterialTheme.colorScheme.primary
    CustomerBalanceStyle.Warning -> MaterialTheme.colorScheme.tertiary
    CustomerBalanceStyle.Overdue -> MaterialTheme.colorScheme.error
}
