package addStudentTest;

import Domain.Student;
import Exceptions.ValidatorException;
import Repository.XMLFileRepository.StudentXMLRepo;
import Validator.StudentValidator;
import org.junit.Test;

public class AddStudentTest  {
    @Test
    public void testAddStudentIdValid(){
        StudentXMLRepo repo=new StudentXMLRepo(new StudentValidator(),"file.xml");

        String id="id";
        Student student=new Student(id,"nume",10,"email","indrumator");
        try {
            assert repo.findOne(id)==null;
            repo.save(student);
            assert repo.findOne(id)==student;
        } catch (ValidatorException e) {
            e.printStackTrace();
            assert false;
        }
    }
}
