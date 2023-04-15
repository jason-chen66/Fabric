package cn.edu.xmu.blockchainandmultimedia.controller;

import cn.edu.xmu.blockchainandmultimedia.controller.vo.CertificationWorkDetailedVo;
import cn.edu.xmu.blockchainandmultimedia.service.CertificationService;
import cn.edu.xmu.blockchainandmultimedia.util.ReturnNo;
import cn.edu.xmu.blockchainandmultimedia.util.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/certification", produces = "application/json;charset=UTF-8")
public class CertificationController {
    private CertificationService certificationService;

    @Autowired
    public CertificationController(CertificationService certificationService){
        this.certificationService = certificationService;
    }

    /**
     * 上传作品所有信息
     *
     * @param
     * @return
     */
    @PostMapping("/uploadworkinfo/{id}")
    public ReturnObject uploadWork(
            @PathVariable Long id,
            @RequestBody CertificationWorkDetailedVo body,
            @RequestParam("file") MultipartFile file){
        certificationService.uploadWorkinfo(id, body);
        try {
            certificationService.cochain(file, id, body);
        } catch (Exception e){
            return new ReturnObject(e);
        }

        return new ReturnObject(ReturnNo.OK);
    }


}
