package Integration;

import Exceptions.ValidatorException;
import Repository.XMLFileRepository.NotaXMLRepo;
import Repository.XMLFileRepository.StudentXMLRepo;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Service.TxtFileService.StudentService;
import Service.XMLFileService.NotaXMLService;
import Service.XMLFileService.StudentXMLService;
import Service.XMLFileService.TemaLabXMLService;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemaLabValidator;
import org.junit.Test;

import java.time.DateTimeException;
import java.util.zip.DataFormatException;

public class IntegrationTesting {

    @Test
    public void testAddStudentIntegration(){
        StudentXMLService studentService=new StudentXMLService(new StudentXMLRepo(new StudentValidator(),"v.xml"));
        String[] studentValid={"1","nume","936","email","teacher"};
        String[] studentInvalid={"1","","936","email","teacher"};

        try {
            studentService.add(studentValid);
            assert(true);
        } catch (ValidatorException e) {
            assert(false);
            e.printStackTrace();
        }

        try {
            studentService.add(studentInvalid);
            assert(false);
        } catch (ValidatorException e) {
            assert(true);
        }
    }

    @Test
    public void testAddAssignmentIntegration(){
        TemaLabXMLService temaLabXMLService=new TemaLabXMLService(new TemaLabXMLRepo(new TemaLabValidator(),"v.xml"));
        String[] assignmentValid = {"1","descriere","8","9","9"};
        String[] assignmentInvalid = {"1","","8","7","7"};

        try {
            temaLabXMLService.add(assignmentValid);
            assert (true);
        } catch (ValidatorException e) {
            e.printStackTrace();
            assert(false);
        }

        try {
            temaLabXMLService.add(assignmentInvalid);
            assert (false);
        } catch (ValidatorException e) {
            assert(true);
        }
    }

    @Test
    public void testAddGradeIntegration(){
        NotaXMLService notaXMLService=new NotaXMLService(new NotaXMLRepo(new NotaValidator(),"v.xml"));
        String[] gradeValid={"1", "1","1","10","2019-04-03T11:44:44.797"};
        String[] gradeDateInvalid={"1", "1","1","10","01-01-2010"};
        String[] gradeIdInvalid={"-1", "1","1","10","2019-04-03T11:44:44.797"};

        try{
            notaXMLService.add(gradeValid);
            assert(true);
        } catch (Exception e){
            e.printStackTrace();
            assert (false);
        }

        try{
            notaXMLService.add(gradeDateInvalid);
            assert (false);
        } catch (DateTimeException e){
            assert true;
        } catch (ValidatorException e){
            assert false;
        }

        try{
            notaXMLService.add(gradeIdInvalid);
            assert false;
        } catch (ValidatorException e){
            assert true;
        }
    }

    @Test
    public void testAddAllIntegration(){
        StudentXMLService studentXMLService = new StudentXMLService(new StudentXMLRepo(new StudentValidator(),"student.xml"));
        TemaLabXMLService temaLabXMLService = new TemaLabXMLService(new TemaLabXMLRepo(new TemaLabValidator(),"tema.xml"));
        NotaXMLService notaXMLService = new NotaXMLService(new NotaXMLRepo(new NotaValidator(),"nota.xml"));

        String[] studentValid={"1","nume","936","email","teacher"};
        String[] assignmentValid = {"1","descriere","8","7"};
        String[] gradeValid={"1", "1","1","10","2019-04-03T11:44:44.797"};

        try{
            studentXMLService.add(studentValid);
            temaLabXMLService.add(assignmentValid);
            notaXMLService.add(gradeValid);
            assert true;
        } catch (ValidatorException v){
            assert false;
        }
    }
}
