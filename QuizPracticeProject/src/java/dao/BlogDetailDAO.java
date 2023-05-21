/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Blog;
import model.User;

/**
 *
 * @author ACER
 */
public class BlogDetailDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Blog getBlogDetail(String id) {
        String query = "select * from [blog]\n"
                + "where id = ?";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Blog(rs.getInt(1),//id
                        rs.getString(2),//thumbnail
                        rs.getInt(3),//author id
                        rs.getString(4),//title
                        rs.getString(5),//category
                        rs.getString(6),//flag
                        rs.getString(7),//status
                        rs.getString(8),//content
                        rs.getDate(9),//created
                        rs.getDate(10)//modified
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

}
