package addStudentTest;

import Domain.TemaLab;
import Exceptions.ValidatorException;
import Repository.XMLFileRepository.StudentXMLRepo;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Validator.TemaLabValidator;
import org.junit.Test;

public class AddAssignmentTest {
    @Test
    public void testAddAssignmentEntityNull(){
        TemaLabXMLRepo repo=new TemaLabXMLRepo(new TemaLabValidator(),"file.xml");

        try{
            repo.save(null);
            assert false;
        } catch (ValidatorException e){
            assert false;
        } catch (IllegalArgumentException e){
            assert true;
        }
    }

    @Test
    public void testAddAssignmentDuplicateId(){
        TemaLabXMLRepo repo=new TemaLabXMLRepo(new TemaLabValidator(),"file.xml");
        TemaLab temaLab1=new TemaLab(1,"descriere",10,8);
        TemaLab temaLab2 = new TemaLab(1, "descr",10,7);

        assert repo.findOne(1)==null;
        try {
            repo.save(temaLab1);
        } catch (ValidatorException e) {
            assert false;
        }
        assert repo.findOne(1)==temaLab1;

        try{
            repo.save(temaLab2);
            assert false;
        } catch (ValidatorException e){
            assert true;
        }
    }

    @Test
    public void testAddAssignmentDescriereInvalid(){
        TemaLabXMLRepo repo=new TemaLabXMLRepo(new TemaLabValidator(),"file.xml");
        TemaLab temaLab1= new TemaLab(1, null,10,8);
        TemaLab temaLab2= new TemaLab(2, "",10,8);

        try{
            repo.save(temaLab1);
            assert false;
        } catch (ValidatorException e){
            assert true;
        }

        try{
            repo.save(temaLab2);
            assert false;
        } catch (ValidatorException e){
            assert true;
        }
    }

    @Test
    public void testAddAssignmentDescriereValid(){
        TemaLabXMLRepo repo=new TemaLabXMLRepo(new TemaLabValidator(),"file.xml");

        String descriere="valid";
        TemaLab temaLab1= new TemaLab(1, descriere,10,8);
        try {
            repo.save(temaLab1);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }
    }

    @Test
    public void testAddAssignmentPredareInvalid(){
        TemaLabXMLRepo repo=new TemaLabXMLRepo(new TemaLabValidator(),"file.xml");
        TemaLab temaLab1= new TemaLab(1, "descriere",10,0);

        try{
            repo.save(temaLab1);
            assert false;
        } catch (ValidatorException e){
            assert true;
        }
    }

    @Test
    public void testAddAssignmentValid(){
        TemaLabXMLRepo repo=new TemaLabXMLRepo(new TemaLabValidator(),"file.xml");

        String descriere="valid";
        TemaLab temaLab1= new TemaLab(1, descriere,10,8);
        try {
            repo.save(temaLab1);
            assert true;
        } catch (ValidatorException e) {
            assert false;
        }
    }

    @Test
    public void testAddAssignmentDeadlineInvalid(){
        TemaLabXMLRepo repo=new TemaLabXMLRepo(new TemaLabValidator(),"file.xml");
        TemaLab temaLab1= new TemaLab(1, "descriere",0,10);

        try{
            repo.save(temaLab1);
            assert false;
        } catch (ValidatorException e){
            assert true;
        }
    }
}
