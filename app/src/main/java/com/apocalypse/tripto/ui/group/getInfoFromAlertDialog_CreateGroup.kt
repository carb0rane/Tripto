package com.apocalypse.tripto.ui.group

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.apocalypse.tripto.R
import kotlinx.android.synthetic.main.alert_dialog_create_group.view.*

class getInfoFromAlertDialog_CreateGroup {
    companion object Factory {
         var state="Jammu & Kashmir"
         var District="Jammu"
        fun get_state(tvg: View, thecont: Context) {
            val array = thecont.resources.getStringArray(R.array.array_indian_states)

            val stateAdapter = ArrayAdapter(thecont, R.layout.spinner_layout, array)
            stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            tvg.spinner_state.adapter = stateAdapter
            tvg.spinner_state.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                   // Log.d("Selected State: ", " : " + tvg.spinner_state.getItemAtPosition(p2))
                    state = tvg.spinner_state.getItemAtPosition(p2).toString()
                    var districtAdapter: ArrayAdapter<CharSequence>
                    districtAdapter = ArrayAdapter.createFromResource(
                        thecont,
                        R.array.array_jammu_kashmir_districts,
                        R.layout.spinner_layout
                    )
                    when (state) {
                        "Select Your State" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_default_districts, R.layout.spinner_layout
                        )

                        "Andhra Pradesh" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_andhra_pradesh_districts, R.layout.spinner_layout
                        )

                        "Arunachal Pradesh" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_arunachal_pradesh_districts, R.layout.spinner_layout
                        )

                        "Assam" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_assam_districts, R.layout.spinner_layout
                        )

                        "Bihar" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_bihar_districts, R.layout.spinner_layout
                        )

                        "Chhattisgarh" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_chhattisgarh_districts, R.layout.spinner_layout
                        )

                        "Goa" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_goa_districts, R.layout.spinner_layout
                        )

                        "Gujarat" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_gujarat_districts, R.layout.spinner_layout
                        )

                        "Haryana" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_haryana_districts, R.layout.spinner_layout
                        )

                        "Himachal Pradesh" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_himachal_pradesh_districts, R.layout.spinner_layout
                        )

                        "Jharkhand" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_jharkhand_districts, R.layout.spinner_layout
                        )

                        "Karnataka" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_karnataka_districts, R.layout.spinner_layout
                        )

                        "Kerala" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_kerala_districts, R.layout.spinner_layout
                        )

                        "Madhya Pradesh" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_madhya_pradesh_districts, R.layout.spinner_layout
                        )

                        "Maharashtra" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_maharashtra_districts, R.layout.spinner_layout
                        )

                        "Manipur" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_manipur_districts, R.layout.spinner_layout
                        )

                        "Meghalaya" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_meghalaya_districts, R.layout.spinner_layout
                        )

                        "Mizoram" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_mizoram_districts, R.layout.spinner_layout
                        )

                        "Nagaland" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_nagaland_districts, R.layout.spinner_layout
                        )


                        "Punjab" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_punjab_districts, R.layout.spinner_layout
                        )

                        "Rajasthan" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_rajasthan_districts, R.layout.spinner_layout
                        )

                        "Sikkim" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_sikkim_districts, R.layout.spinner_layout
                        )

                        "Tamil Nadu" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_tamil_nadu_districts, R.layout.spinner_layout
                        )

                        "Telangana" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_telangana_districts, R.layout.spinner_layout
                        )

                        "Tripura" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_tripura_districts, R.layout.spinner_layout
                        )

                        "Uttar Pradesh" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_uttar_pradesh_districts, R.layout.spinner_layout
                        )

                        "Uttarakhand" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_uttarakhand_districts, R.layout.spinner_layout
                        )

                        "West Bengal" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_west_bengal_districts, R.layout.spinner_layout
                        )

                        "Andaman and Nicobar Islands" -> districtAdapter =
                            ArrayAdapter.createFromResource(
                                thecont,
                                R.array.array_andaman_nicobar_districts, R.layout.spinner_layout
                            )

                        "Chandigarh" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_chandigarh_districts, R.layout.spinner_layout
                        )

                        "Dadra and Nagar Haveli" -> districtAdapter =
                            ArrayAdapter.createFromResource(
                                thecont,
                                R.array.array_dadra_nagar_haveli_districts, R.layout.spinner_layout
                            )

                        "Daman and Diu" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_daman_diu_districts, R.layout.spinner_layout
                        )

                        "Delhi" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_delhi_districts, R.layout.spinner_layout
                        )

                        "Jammu and Kashmir" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_jammu_kashmir_districts, R.layout.spinner_layout
                        )

                        "Lakshadweep" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_lakshadweep_districts, R.layout.spinner_layout
                        )

                        "Ladakh" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_ladakh_districts, R.layout.spinner_layout
                        )

                        "Puducherry" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_puducherry_districts, R.layout.spinner_layout
                        )
                        "Orrisa" -> districtAdapter = ArrayAdapter.createFromResource(
                            thecont,
                            R.array.array_odisha_districts, R.layout.spinner_layout
                        )


                    }
                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    tvg.spinner_district.adapter = districtAdapter
                    tvg.spinner_district.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                p0: AdapterView<*>?,
                                p1: View?,
                                p2: Int,
                                p3: Long
                            ) {
                                District=tvg.spinner_district.getItemAtPosition(p2).toString()

                            }


                            override fun onNothingSelected(p0: AdapterView<*>?) {
                            }
                        }

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Log.d("Selected State: ", " None")
                }
            }

        }

    }
    }
