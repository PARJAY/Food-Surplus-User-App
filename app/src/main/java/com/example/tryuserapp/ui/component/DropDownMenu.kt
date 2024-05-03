package com.example.tryuserapp.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.tryuserapp.MyApp
import com.example.tryuserapp.data.model.YayasanModel
import com.example.tryuserapp.tools.Utility

@Composable
fun DropDownYayasan(
    setLokasiYayasan : (String) -> Unit,
    isPreferGetLocationWithMaps : Boolean
) {

    val context = LocalContext.current


    val listYayasanAnda = remember { mutableStateListOf<YayasanModel>() }

    LaunchedEffect (Unit) {
        MyApp.appModule.yayasanRepositoryImpl.getYayasanList(
            errorCallback = {
                Utility.showToast(context, "error : $it")
                Log.d("Yayasan", "error : $it")
            },
            addDataCallback = {
                listYayasanAnda.add(it)
                listYayasanAnda.sortedBy { it -> it.namaYayasan }
                Log.d("Yayasan", "added to screen : $it")
            },
            updateDataCallback = { updatedData ->
                val index = listYayasanAnda.indexOfFirst { it.idYayasan == updatedData.idYayasan }
                if (index != -1) listYayasanAnda[index] = updatedData
                listYayasanAnda.sortedBy { it.namaYayasan }
                Log.d("Yayasan", "updated to screen : $updatedData")
            },
            deleteDataCallback = { documentId: String ->
                listYayasanAnda.removeAll { it.idYayasan == documentId }
                listYayasanAnda.sortedBy { it.namaYayasan}
                Log.d("Yayasan", "deleted from screen : id = $documentId")
            }
        )
    }

    var isExpanded by remember { mutableStateOf(false) }

    var selectedYayasanName by remember { mutableStateOf("Pilih Yayasan") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },

            modifier = Modifier.align(Alignment.End)
        ) {
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                listYayasanAnda.forEachIndexed { index, yayasan ->
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        text = { Text(text = yayasan.namaYayasan) },
                        enabled = !isPreferGetLocationWithMaps,
                        onClick = {
                            selectedYayasanName = yayasan.namaYayasan
                            setLokasiYayasan(yayasan.alamatYayasan)
                            isExpanded = false
                        }
                    )
                }
            }
        }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = selectedYayasanName,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Dropdown Menu"
                    )
                }
            }
        )
    }
}