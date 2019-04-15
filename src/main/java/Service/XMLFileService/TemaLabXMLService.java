package Service.XMLFileService;

import Domain.TemaLab;
import Exceptions.ValidatorException;
import Repository.XMLFileRepository.TemaLabXMLRepo;

public class TemaLabXMLService extends AbstractXMLService<Integer,TemaLab>{
    private TemaLabXMLRepo xmlrepo;

    public TemaLabXMLService(TemaLabXMLRepo xmlrepo)  {
        super(xmlrepo);
    }

    public void prelungireTemaLab(String nr,String descr,String sl,String sp,int sc) throws ValidatorException {
        if(sc<=Integer.parseInt(sp)){
            String sln=Integer.toString(Integer.parseInt(sl)+1) ;
            String[] params={nr,descr,sln,sp};
            update(params);
        }

    }
    @Override
    protected TemaLab extractEntity(String[] params){
        Integer id=0,deadline=0,received=0;
        try {
            id=Integer.parseInt(params[0]);
        }catch (NumberFormatException e){
            System.out.println("Invalid id");
        }
        try {
            deadline=Integer.parseInt(params[2]);
        }catch (NumberFormatException e){
            System.out.println("Invalid deadline");
        }
        try {
            received=Integer.parseInt(params[3]);
        }catch (NumberFormatException e){
            System.out.println("Invalid received week");
        }
        return new TemaLab(id, params[1], deadline, received);
    }

}