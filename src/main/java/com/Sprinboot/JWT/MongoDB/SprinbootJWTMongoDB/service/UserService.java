package com.Sprinboot.JWT.MongoDB.SprinbootJWTMongoDB.service;

import com.Sprinboot.JWT.MongoDB.SprinbootJWTMongoDB.model.User;
import com.Sprinboot.JWT.MongoDB.SprinbootJWTMongoDB.repository.UserRepository;
import org.apache.catalina.util.ToStringUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repo;


    private TokenService service;

    @Autowired
    public UserService(UserRepository repo, TokenService service) {
        this.repo = repo;
        this.service = service;
    }

    public String login(String email,String password)
    {

        Optional<User> optionalUser = Optional.ofNullable(repo.findByEmail(email));
        if(optionalUser.isPresent())
        {
            String pswrd=optionalUser.get().getPassword();
            String str=service.createToken((optionalUser.get().getId()));
            if(pswrd.equals(password))
                return "successfull login"+
                        "\tdata:{\n \t\t\"id\"="+optionalUser.get().getId()+"\",\n"
                        +"\t\t\"name\"=\'"+optionalUser.get().getName()+"\',\n"+
                        "\t\t\"email\"=\'"+optionalUser.get().getEmail()+"\',\n"+
                        "\t\t\"password\"=\'"+optionalUser.get().getPassword()+"\',"+"\n\t},\n"+
                        "\t\t\"token\"=\'"+str+"\',"+"\n\t},\n"+
                        "}";

        }
        return  "user not present";
    }

    public String signUp(User user)
    {
        User savedUser= repo.save(user);

        String str=service.createToken((savedUser.getId()));
        return ("Output:{\n" +
                "\tmessage : Successfully Signed Up User,\n" +
                "\tdata:{\n \t\t\"id\"="+savedUser.getId()+"\",\n"
                +"\t\t\"name\"=\'"+savedUser.getName()+"\',\n"+
                "\t\t\"email\"=\'"+savedUser.getEmail()+"\',\n"+
                "\t\t\"password\"=\'"+savedUser.getPassword()+"\',"+"\n\t},\n}");
    }

    public List<User> getAllUsers()
    {
        return repo.findAll();
    }

    }


