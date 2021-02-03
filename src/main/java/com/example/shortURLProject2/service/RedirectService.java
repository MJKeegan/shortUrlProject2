package com.example.shortURLProject2.service;

import com.example.shortURLProject2.entity.Redirect;
import com.example.shortURLProject2.exception.BadRequestException;
import com.example.shortURLProject2.repository.RedirectRepository;
import com.example.shortURLProject2.request.RedirectCreationRequest;
import com.example.shortURLProject2.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedirectService {

    private RedirectRepository redirectRepository;

    @Autowired
    public RedirectService(RedirectRepository redirectRepository) {
        this.redirectRepository = redirectRepository;
    }

    public Optional<Redirect> createRedirect(RedirectCreationRequest redirectCreationRequest){
        if(redirectRepository.existsByAlias(redirectCreationRequest.getAlias()))
        {
            throw new BadRequestException("alias already exists");
        }

        System.out.println("Redirect Request " + redirectCreationRequest.toString());
        Redirect redirect = redirectRepository.save(new Redirect(redirectCreationRequest.getAlias(), redirectCreationRequest.getUrl()));
        System.out.println("Redirect" + redirect);

        return Optional.ofNullable(redirect);
    }

    public Redirect getRedirect(String alias) {
        Redirect redirect = redirectRepository.findByAlias(alias)
                .orElseThrow(() -> new NotFoundException("Hey we don't have that alias ! Try making it"));
        return redirect;
    }
}


