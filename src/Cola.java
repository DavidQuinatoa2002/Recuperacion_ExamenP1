import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Cola {
    private Queue<Persona> personas;

    public Cola(){
        personas = new PriorityQueue<Persona>();

    }

    public void encolar(String cedula, String nombre, int edad, String genero, String region) throws Exception {
        if(buscarPersona(cedula)==null)
            personas.add(new Persona(cedula,nombre,edad,genero,region));
        else
            throw new Exception("la cedula ya esta registrada");
    }

    public List<Persona> listarPersonas(){
        List<Persona> lista = new LinkedList<>();
        for(Persona p:personas)
            lista.add(p);
        return lista;
    }

    public Persona buscarPersona(String cedula){
        for(Persona p:personas)
            if(p.getCedula().equals(cedula))
                return p;

        return null;

    }

    public List<Persona> listaEdadSexo(int edad, String sexo)throws Exception{
        if(personas.isEmpty())
            throw new Exception("no hay personas registradas");
        else{
            List<Persona> lista = new LinkedList<>();
            for(Persona p:personas){
                if(p.getEdad()==edad && p.getGenero().equals(sexo))
                    lista.add(p);
            }

            return lista;

        }


    }





    public List<Persona> listaEdadRegion(int edad, String region)throws Exception{
        if(personas.isEmpty())
            throw new Exception("no hay personas registradas");
        else{
        List<Persona> lista = new LinkedList<>();
        for(Persona p:personas){
            if(p.getEdad()==edad && p.getRegion().equals(region))
                lista.add(p);
        }

        return lista;

        }
    }

}
