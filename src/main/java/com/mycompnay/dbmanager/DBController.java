package com.mycompnay.dbmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.mycompany.modal.Rules;

public class DBController {

	
	public static void insertIntodb(List<Rules> list)
	{
		String sql = "insert into rules_list(GROUP_NAME,RULE,CREATED_TIME,CREATED_BY) value(?,?,now(),'SYSTEM')";
		try(Connection connection = DataService.loadDriver();
				PreparedStatement ps = connection.prepareStatement(sql))
				{
			for(Rules rule : list)
			{
				ps.setString(1, rule.getGroupName().trim());
				ps.setString(2, rule.getRule().trim());
				ps.addBatch();
			}
			ps.executeBatch();
				}catch (Exception e) {
					e.printStackTrace();
				}
	}
}
