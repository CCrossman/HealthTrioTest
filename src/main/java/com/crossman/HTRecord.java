package com.crossman;

import lombok.Data;

@Data
public class HTRecord {
	private String region;
	private String region_code;
	private Integer period;
	private String pct_hospitals_basic_ehr_notes;
	private String pct_rural_hospitals_basic_ehr_notes;
	private String pct_small_hospitals_basic_ehr_notes;
	private String pct_critical_access_hospitals_basic_ehr_notes;
	private String pct_hospitals_basic_ehr_no_notes;
	private String pct_rural_hospitals_basic_ehr_no_notes;
	private String pct_small_hospitals_basic_ehr_no_notes;
	private String pct_critical_access_hospitals_basic_ehr_no_notes;
	private String pct_hospitals_cehrt;
	private String pct_small_rural_hospitals_cehrt;
	private String pct_cah_hospitals_cehrt;
	private String pct_hospitals_share_labs_any_outside_provs;
	private String pct_hospitals_share_labs_any_outside_hospitals;
	private String pct_hospitals_share_labs_any_outside_ambu_provs;
	private String pct_hospitals_patients_ecopy_ehr;
	private String pct_hospitals_patients_ecopy_discharge_instr;
	private String pct_hospitals_share_care_summaries_any_outside_provs;
	private String pct_hospitals_share_care_summaries_any_outside_hospitals;
	private String pct_hospitals_share_care_summaries_any_outside_ambu_provs;
	private String pct_hospitals_patients_vdt;
	private String pct_hospitals_patients_secure_message;
	private String pct_hospitals_find_clinical_info;
	private String pct_hospitals_send_clinical_info;
	private String pct_hospitals_receive_clinical_info;
	private String pct_hospitals_integrate_any_clinical_info;
	private String pct_hospitals_integrate_scr;
}
