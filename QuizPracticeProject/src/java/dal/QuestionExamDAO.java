/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author Acer
 */
public class QuestionExamDAO extends MyDAO {

    public int getQuestionIdByExamId(int examId, int page) {
        try {
            String strSlect = "select * from question_exam "
                    + "where exam_id=? AND question_order=?";
            ps = con.prepareCall(strSlect);
            ps.setInt(1, examId);
            ps.setInt(2, page);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(2);
            }
        } catch (Exception e) {
            System.out.println("getQuestionIdByExamId: " + e.getMessage());
        }
        return 0;
    }

    public int countExamQuestion(int examId) {
        try {
            String strCount = "SELECT COUNT(*) AS question_count\n"
                    + "FROM question_exam\n"
                    + "WHERE exam_id = ?";
            ps = con.prepareCall(strCount);
            ps.setInt(1, examId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countExamQuestion: " + e.getMessage());
        }
        return 0;
    }

}
