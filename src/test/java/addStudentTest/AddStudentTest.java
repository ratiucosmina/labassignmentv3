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

    @Test
    public void testAddStudentIdInvalid(){
        StudentXMLRepo repo=new StudentXMLRepo(new StudentValidator(),"file.xml");

        String id="";
        Student student=new Student(id,"nume",10,"email","indrumator");
        try {
            repo.save(student);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }
    }

    @Test
    public void testAddStudentNameValid(){
        StudentXMLRepo repo=new StudentXMLRepo(new StudentValidator(),"file.xml");

        String nume="nume";
        Student student=new Student("id",nume,10,"email","indrumator");
        try {
            assert repo.findOne("id")==null;
            repo.save(student);
            assert repo.findOne("id")==student;
        } catch (ValidatorException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void testAddStudentNameInvalid(){
        StudentXMLRepo repo=new StudentXMLRepo(new StudentValidator(),"file.xml");

        String nume="";
        Student student=new Student("id",nume,10,"email","indrumator");
        try {
            repo.save(student);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }
    }

    @Test
    public void testAddStudentGroupValid(){
        StudentXMLRepo repo=new StudentXMLRepo(new StudentValidator(),"file.xml");

        int grupa=936;
        Student student=new Student("id","nume",grupa,"email","indrumator");
        try {
            assert repo.findOne("id")==null;
            repo.save(student);
            assert repo.findOne("id")==student;
        } catch (ValidatorException e) {
            assert false;
        }
    }

    @Test
    public void testAddStudentGroupInvalid(){
        StudentXMLRepo repo=new StudentXMLRepo(new StudentValidator(),"file.xml");

        Student student=new Student("id","nume",0,"email","indrumator");
        try {
            repo.save(student);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }
    }

    @Test
    public void testAddStudentEmailValid(){
        StudentXMLRepo repo=new StudentXMLRepo(new StudentValidator(),"file.xml");

        Student student=new Student("id","nume",10,"email","indrumator");
        try {
            assert repo.findOne("id")==null;
            repo.save(student);
            assert repo.findOne("id")==student;
        } catch (ValidatorException e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    public void testAddStudentEmailInvalid(){
        StudentXMLRepo repo=new StudentXMLRepo(new StudentValidator(),"file.xml");

        String nume="";
        Student student=new Student("id","nume",10,"","indrumator");
        try {
            repo.save(student);
            assert false;
        } catch (ValidatorException e) {
            assert true;
        }
    }

    @Test
    public void testAddStudentNameNull(){
        StudentXMLRepo repo=new StudentXMLRepo(new StudentValidator(),"file.xml");

        String nume="";
        Student student=new Student("id",null,10,"","indrumator");
        try {
            repo.save(student);
            assert false;
        } catch (ValidatorException e) {
            assert false;
        } catch (NullPointerException e) {
            assert true;
        }
    }

}
