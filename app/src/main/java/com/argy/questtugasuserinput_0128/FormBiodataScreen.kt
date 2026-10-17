package com.argy.questtugasuserinput_0128

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormBioDataScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // State Variables
    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tglLahir by remember { mutableStateOf("") }
    var selectedExp by remember { mutableStateOf("") }
    var isExpExpanded by remember { mutableStateOf(false) }

    var isAndroidChecked by remember { mutableStateOf(false) }
    var isUiUxChecked by remember { mutableStateOf(false) }
    var isWebChecked by remember { mutableStateOf(false) }

    val expOptions = stringArrayResource(id = R.array.exp_options)

    // DatePicker
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            tglLahir = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.card_bg))
    ){
        // Gambar Background Header
        Image(
            painter = painterResource(id = R.drawable.umyfoto),
            contentDescription = stringResource(id = R.string.cd_header_bg),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.header_height))
        )

        // Card Utama Mengapung
        Card(
            shape = RoundedCornerShape(
                topStart = dimensionResource(id = R.dimen.card_corner_radius),
                topEnd = dimensionResource(id = R.dimen.card_corner_radius)
            ),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.card_bg)),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = dimensionResource(id = R.dimen.card_top_padding))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(dimensionResource(id = R.dimen.padding_large)),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                // Judul & Subjudul
                Text(
                    text = stringResource(id = R.string.form_title),
                    fontSize = dimensionResource(id = R.dimen.text_title).value.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.purple_title)
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_small)))

                Text(
                    text = stringResource(id = R.string.form_subtitle),
                    fontSize = dimensionResource(id = R.dimen.text_subtitle).value.sp,
                    color = colorResource(id = R.color.subtitle_gray)
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_large)))

                // Row Nama Depan & Nama Belakang
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_small))
                ) {
                    OutlinedTextField(
                        value = namaDepan,
                        onValueChange = { namaDepan = it },
                        label = { Text(stringResource(id = R.string.label_nama_depan)) },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = namaBelakang,
                        onValueChange = { namaBelakang = it },
                        label = { Text(stringResource(id = R.string.label_nama_belakang)) },
                        modifier = Modifier.weight(1f),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_medium)))

                // Input Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(stringResource(id = R.string.label_email)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_medium)))

                // Row Tanggal Lahir (DatePicker) & Experience (Dropdown)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_small))
                ) {
                    // Tgl Lahir
                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedTextField(
                            value = tglLahir,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text(stringResource(id = R.string.label_tgl_lahir)) },
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_date_range_24),
                                    contentDescription = stringResource(id = R.string.cd_calendar_icon)
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clickable { datePickerDialog.show() }
                        )
                    }

                    ExposedDropdownMenuBox(
                        expanded = isExpExpanded,
                        onExpandedChange = { isExpExpanded = !isExpExpanded },
                        modifier = Modifier.weight(1f)
                    ){
                        OutlinedTextField(
                            value = selectedExp,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text(stringResource(id = R.string.label_experience)) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpExpanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = isExpExpanded,
                            onDismissRequest = { isExpExpanded = false }
                        ) {
                            expOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(text = option) },
                                    onClick = {
                                        selectedExp = option
                                        isExpExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_large)))

                // Checkbox Skills
                Text(
                    text = stringResource(id = R.string.title_skill),
                    fontSize = dimensionResource(id = R.dimen.text_section).value.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = isAndroidChecked,
                            onCheckedChange = { isAndroidChecked = it }
                        )
                        Text(text = stringResource(id = R.string.skill_android))
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = isUiUxChecked,
                            onCheckedChange = { isUiUxChecked = it }
                        )
                        Text(text = stringResource(id = R.string.skill_uiux))
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = isWebChecked,
                            onCheckedChange = { isWebChecked = it }
                        )
                        Text(text = stringResource(id = R.string.skill_web))
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.space_large)))

                // Tombol Proceed
                Button(
                    onClick = {
                        // Menampilkan pesan saat tombol diklik
                        Toast.makeText(
                            context,
                            "Biodata $namaDepan $namaBelakang berhasil disimpan!",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.btn_corner_radius)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.btn_bg)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.btn_height))
                ) {
                    Text(
                        text = stringResource(id = R.string.btn_proceed),
                        color = colorResource(id = R.color.btn_text_color),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}