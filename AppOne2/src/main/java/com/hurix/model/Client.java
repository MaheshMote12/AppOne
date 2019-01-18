package com.hurix.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CLIENT")
@Getter @Setter
public class Client {
	
	@Id
	private Long id;
	private  String name;
	private String logo_Url;
	private String color_Skim ;
	  
	  
//	  private Date expiry ; /*timestamp */
//	  private Date createdOn; /*timestamp */
	  
//	  private int deleted;
	  
//	  private int disabled;
//	  private Date startDate;/* timestamp */
	  
//	  private int days;
	  
//	  private int license;
	  private String theme_Name;
	  private String theme_Url;
	  
//	  private boolean  syncing;
//	  @ColumnDefault(value = "true")
//	  private boolean analytics;
	  
//	  private boolean print;
	  
	  @Column()
	  private Long admin_Id;
	  
	  private Long type; /*"TYPE" bigint(10) DEFAULT '1'*/
	  
	  private String schemaName;
	  private String transfer_details;  
	  
	  @Lob
	  @Column(name = "browse_path", columnDefinition = "TEXT")
	  private String browse_path;
	  
	  @Column(length=3000)
	  private String transfer_details_json; 
	  
//	  private String theme;
	  
	/*
	 * "BULK_UPLOAD" varchar(100) DEFAULT NULL, "REDIRECT_URL" varchar(1000) DEFAULT
	 * 'http://172.18.10.122:8280/DistributionServices/', "CLIENT_KEY" varchar(6)
	 * DEFAULT NULL, "CONSUMER_KEY" varchar(100) DEFAULT NULL, "SHARED_SECRET"
	 * varchar(100) DEFAULT NULL, "READER_KEY" varchar(50) DEFAULT
	 * 'cHJvZi1raXRhYm9v', "TRANSFER_DETAILS_JSON" varchar(3000) DEFAULT '{"config":
	 * {"online_transfer": { "mode": "direct","location":
	 * "/home/dicteraqc/workspace/ContentServer/", "ip": "172.18.10.147","username":
	 * "kitabooftp","password": "kitaboo@4321", "csUrl": "http://qc.kitaboo.com/"},
	 * "offline_transfer": {"mode": "direct","location":
	 * "/home/dicteraqc/workspace/ContentServer/","ip": "172.18.10.147","username":
	 * "kitabooftp","password": "kitaboo@4321","csUrl": "http://qc.kitaboo.com/"}}}'
	 * COMMENT 'client ftp/s3/direct details in json format', "PARENT_ID" bigint(10)
	 * DEFAULT NULL, "S3_REGION_ID" bigint(20) DEFAULT '1', "EXPIRY_NOTIFY"
	 * varchar(10) DEFAULT 'FALSE', "JOB_STATUS" int(1) DEFAULT '0', "RESPONSE"
	 * longtext, "OWNER" varchar(256) DEFAULT 'OPEN', "UPDATED_ON" datetime DEFAULT
	 * NULL,
	 */
}
