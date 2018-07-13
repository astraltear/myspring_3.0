package com.auiden.b2c;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.audien.common.crypt.MakeMD5;
import com.audien.common.crypt.SHA256Utils;
import com.audien.common.crypt.SeedSecurity;

import static org.junit.Assert.*;

public class PasswordSocialNoTest {
	
	private final static String REAL = "jdbc:oracle:thin:@211.189.50.86:1521:ORA9";
	private final static String USER = "audien";
	private final static String PW = "dheldjs";
	
	private final static String PWQUERY = "select GETMD5(?) as encrypt from dual union all  select GETSHA256(GETMD5(?)) as encrypt from dual ";
	private final static String SOCIALNOQUERY = "select SEEDENC(?) as encrypt from dual union all select SEEDDEC(SEEDENC(?)) as encrypt from dual ";

	@Test
	public void test() {
		String password = genText();
		String socialNo = genSN();
		
		List<String> dbList = getDBEncrypt(password, PWQUERY, socialNo, SOCIALNOQUERY);
		
		String dbMD5 = dbList.get(0);
		String dbSHA256 = dbList.get(1);

		String javaMD5="";
		String javaSHA256="";
		try {
			javaMD5 = MakeMD5.getMD5(password);
			javaSHA256 = SHA256Utils.getSHA256(MakeMD5.getMD5(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		System.out.println("java MD5[" + javaMD5 + "]");
		System.out.println("db   MD5[" + dbMD5 + "]");
		System.out.println("java SHA256[" + javaSHA256 + "]");
		System.out.println("db   SHA256[" + dbSHA256 + "]");
		
		assertEquals(javaMD5, dbMD5);
		assertEquals(javaSHA256, dbSHA256);

		String dbSeedEnc = dbList.get(2);
		String dbSeedDec = dbList.get(3);

		String javaSeedEnc = SeedSecurity.encrypt(socialNo);
		String javaSeedDec = SeedSecurity.decrypt(javaSeedEnc);

		System.out.println("java SEEDENC[" + javaSeedEnc + "]");
		System.out.println("db   SEEDDEC[" + dbSeedEnc + "]");
		System.out.println("java SEEDDEC[" + javaSeedDec + "]");
		System.out.println("db   SEEDDEC[" + dbSeedDec + "]");
		
		assertEquals(javaSeedEnc, dbSeedEnc);
		assertEquals(javaSeedDec, dbSeedDec);
		
	}
	
	public String genText() {
		char[] availCharArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		int lenRandom = randomRange(8, 12);

		int choose = 0;
		StringBuffer strbuf = new StringBuffer();
		for (int i = 0; i < lenRandom; i++) {
			choose = randomRange(1, 62);
			strbuf.append(availCharArray[choose - 1]);
		}
//		System.out.println("lenRandom[" + lenRandom + "] GEN STRING SIZE[" + strbuf.length() + "] GEN STRING[" + strbuf.toString() + "]");

		return strbuf.toString();
	}
	
	public String genSN() {
		char[] availCharArray = "1234567890".toCharArray();

		int choose = 0;
		StringBuffer strbuf = new StringBuffer();
		for (int i = 0; i < 13; i++) {
			choose = randomRange(1, 10);
			if (i == 6) {
				strbuf.append("-");
			}
			strbuf.append(availCharArray[choose - 1]);
		}
//		System.out.println("GEN STRING SIZE[" + strbuf.length() + "] GEN STRING[" + strbuf.toString() + "]");

		return strbuf.toString();
	}
	
	public int randomRange(int n1, int n2) {
		return (int) (Math.random() * (n2 - n1 + 1)) + n1;
	}
	
	public List<String> getDBEncrypt(String pwText, String pwQuery, String snText, String snQuery) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List dbEncryptList = new ArrayList<String>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(REAL, USER, PW);

			StringBuffer strbuf = new StringBuffer();
			strbuf.append(pwQuery);

			pstmt = con.prepareStatement(strbuf.toString());
			pstmt.setString(1, pwText);
			pstmt.setString(2, pwText);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dbEncryptList.add(rs.getString("encrypt"));
			}

			strbuf.setLength(0);
			strbuf.append(snQuery);

			pstmt = con.prepareStatement(strbuf.toString());
			pstmt.setString(1, snText);
			pstmt.setString(2, snText);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dbEncryptList.add(rs.getString("encrypt"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}

		return dbEncryptList;
	}

}
