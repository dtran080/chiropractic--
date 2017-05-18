package chirodata;
import java.sql.*;
import java.util.*;
import org.sqlite.*;
public class Driver {
	private static List<String> websites;
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		String sDriverName = "org.sqlite.JDBC";
	    Class.forName(sDriverName);
	    websites= new ArrayList<String>();
	    // now we set up a set of fairly basic string variables to use in the body of the code proper
	    String sTempDb = "chiro1.sqlite";
	    String sJdbc = "jdbc:sqlite";
	    String sDbUrl = sJdbc + ":" + sTempDb;
	    // which will produce a legitimate Url for SqlLite JDBC :
	    // jdbc:sqlite:chiro1.sqlite
	    int iTimeout = 30;
	    String sql = "Select * from chiro;";

	    // create a database connection
	    Connection conn = DriverManager.getConnection(sDbUrl);
	    try {
	        Statement stmt = conn.createStatement();
	        try {
	            stmt.setQueryTimeout(iTimeout);
	            ResultSet rs = stmt.executeQuery(sql);
	            try {
	                while(rs.next())
	                    {/*
	                		String record = rs.getString("record");*/
	                        String sResult = rs.getString("URL");
	                        System.out.println(sResult);
	                        websites.add(sResult);
	                    }
	            } finally {
	                try { rs.close(); } catch (Exception ignore) {}
	            }
	        } finally {
	            try { stmt.close(); } catch (Exception ignore) {}
	        }


	    } finally {
	        try { conn.close(); } catch (Exception ignore) {}
	    }
	  //obtain List<String> websites
	    //Table chiro-test is already created inside sqlite server
/*	    String create-chiro-table = "
    String create-chiro-table = "CREATE TABLE [123] (
    record                        INTEGER,
    DC                            TEXT,
    license number                INTEGER,
    URL                           TEXT,
    DCs                           INTEGER,
    details in record             TEXT,
    identy of author known        INTEGER,
    if yes who                    TEXT,
    Chiro specific web designer   TEXT,
    subluxation                   TEXT,
    comment                       TEXT,
    S complex                     TEXT,
    comment 1                     TEXT,
    Vert S                        TEXT,
    comment 2                     TEXT,
    VSC                           TEXT,
    comment 3                     TEXT,
    description                   TEXT,
    picture                       TEXT,
    Nerve system                  TEXT,
    N Interference                TEXT,
    Warning signs                 TEXT,
    Meric Chart                   TEXT,
    principled                    TEXT,
    straight                      TEXT,
    Wellness                      TEXT,
    Link to wellness library      TEXT,
    Nondrug drugleswebs              TEXT,
    Innate universal intelligence TEXT,
    Vitalism Vitalistic           TEXT,
    Religious content             TEXT,
    subluxation 2                 TEXT,
    NMS                           TEXT,
    sports injury                 TEXT,
    WC                            TEXT,
    sprain strain                 TEXT,
    PI                            TEXT,
    whiplash                      TEXT,
    SRD                           TEXT,
    Other non MSK                 TEXT,
    allergy sensitivity           TEXT,
    asthma                        TEXT,
    otitis media ear infection    TEXT,
    colic                         TEXT,
	ADHD                          TEXT,
    comment 4                     TEXT,
    Spine disease                 TEXT,
    Maintenance prevention        TEXT,
    wellness care                 TEXT,
    superior outcomes             TEXT,
    Rx Surg Bad                   TEXT,
    Vaccination                   TEXT,
    stroke risk                   TEXT,
    marketing tactics             TEXT,
    PRIMARY KEY (
        record
    )
);*/
		 String[] keywords={ "DC","doctor", "license number","links: ", "DCs", "details in record",
	    "identy of author known","author",
	    "web","designer",    "subluxation", "comment", "sublaxation complex",
	    "vertex","vertebrae", "VSC", "comment", "description",
	    "picture", /*picture found*/  "Nerve system", "N Interference", "Warning signs",
	    "Meric Chart", "principled", "straight", "Wellness",
	    "Wellness library","nondrug", "drugless",
	    "Innate universal intelligence","Innate","universal","intelligence",
	    "Vitalism Vitalistic", "vitalism","vitalistic",
	    "Religious content","evangelical","god","religion","bible","christian",
	    "subluxation", "NMS",  "sports injury",  "WC",  "sprain strain",  "PI",  "whiplash", 
	    "SRD", "Other non MSK", "MSK", "allergy sensitivity", "asthma", "otitis media ear infection",
	    "colic", "ADHD", "comment 4", "Spine disease", "Maintenance prevention",
	    "wellness care","superior outcomes","Rx Surgery Bad","Vaccination","stroke risk","marketing tactics","marketing","market"};
		/*String[][] keywords1={ {sql_column,keywords,(if_yes_who,comment}
				{"identity_of_author_known","author","book","if_yes_who"},
				{"Chiro_specific_web_designer","web","designer"},
				{"subluxation","subluxation","comment"},
				{"S_complex","subluxation complex","comment_1"},
				{"Vert_S","vertebral subluxation","comment_2"},
				{"VSC","vertebral sublaxation complex","VSC"}
				
		};*/
		HashMap<String,String[]> kw = new HashMap<>();
		kw.put("identity_of_author_known",new String[]{"author","book","if_yes_who"});
		kw.put("Chiro_specific_web_designer",new String[]{"web","designer"});
		kw.put("subluxation",new String[]{"subluxation","comment"});
		kw.put("S_complex",new String[]{"subluxation complex","comment_1"});
		kw.put("Vert_S",new String[]{"vertebral subluxation","comment_2"});
		kw.put("VSC",new String[]{"vertebral sublaxation complex","VSC","comment_3"});
		kw.put("description", new String[]{"description"});
		kw.put("picture", new String[]{""});
		kw.put("Nerve_system", new String[]{"nerve systems"});
		kw.put("N_Interference", new String[]{"interference","nerve"});
		kw.put("Warning_signs", new String[]{"warning sign"});
		kw.put("Meric_Chart", new String[]{"meric chart"});
		kw.put("principled", new String[]{"principle"});
		kw.put("straight", new String[]{"straight"});
		kw.put("Wellness", new String[]{"wellness"});
		kw.put("Link_to_wellness_library", new String[]{"wellness library"});
		kw.put("Nondrug_durgless", new String[]{"drugless","nondrug","drug"});
		kw.put("innate_universal_intelligence", new String[]{"innate","universal","intelligence"});
		kw.put("vitalism_vitalistic", new String[]{"vitalism vatalistic"});
		kw.put("Religious_content", new String[]{"religion","god","christ"});
		//kw.put("subluxation_2", new String[]{"sublixation"});
		kw.put("NMS", new String[]{"NMS","nms chiropractic"});
		kw.put("sports_injury", new String[]{"sport injur","injur","sport"});
		kw.put("WC", new String[]{"wc"});
		kw.put("sprain_strain", new String[]{"Sprain strain"});
		kw.put("PI", new String[]{"PI"});
		kw.put("whiplash", new String[]{"whiplash"});
		kw.put("SRD", new String[]{"SRD"});
		kw.put("Other_non_MSK", new String[]{"non MSK"});
		kw.put("allergy_sensitivity", new String[]{"allergy sensitivity"});
		kw.put("asthma", new String[]{"asthma"});
	}
}

