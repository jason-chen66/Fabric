package cn.edu.xmu.blockchainandmultimedia.controller;

import cn.edu.xmu.blockchainandmultimedia.controller.vo.WorkDetailedModifyVo;
import cn.edu.xmu.blockchainandmultimedia.service.WorkManagementService;
import cn.edu.xmu.blockchainandmultimedia.util.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/mywork", produces = "application/json;charset=UTF-8")
public class WorkManagementController {
    private WorkManagementService workManagementService;

    @Autowired
    public WorkManagementController(WorkManagementService workManagementService){
        this.workManagementService = workManagementService;
    }

    /**
     * 查看自己作品简略信息
     *
     * @param
     * @return
     */
    @GetMapping("/{id}")
    public ReturnObject retrieveWorkByUserId(@PathVariable Long id) {
        return new ReturnObject(workManagementService.retrieveWorkByAuthorId(id, 1, 10)); //id, page, pageSize
    }

    /**
     * 查看自己作品详细信息
     *
     * @param
     * @return
     */
    @GetMapping("/detailed/{workId}")
    public ReturnObject findWorkDetailedById(@PathVariable Long workId){
        return new ReturnObject(workManagementService.retrieveWorkDetailedById(workId)); //id, page, pageSize
    }

    /**
     * 修改自己作品详细信息
     *
     * @param
     * @return
     */
    @PutMapping("/detailed/{workId}")
    public ReturnObject updateWorkDetailedById(
            @PathVariable Long workId,
            @RequestBody WorkDetailedModifyVo body){
        return new ReturnObject(workManagementService.updateWorkDetailedById(workId, body));
    }

    /**
     * 查看自己作品证书状态
     *
     * @param
     * @return
     */
    @GetMapping("/certificate/status/{workId}")
    public ReturnObject findCertificateStatus(@PathVariable Long workId){
        return new ReturnObject(workManagementService.findCertificateStatus(workId));
    }

    /**
     * 生成证书/查看证书
     *
     * @param
     * @return
     */
    @PostMapping("/certificate/{workId}")
    public ReturnObject generateCertificate(@PathVariable Long workId){
        return new ReturnObject(workManagementService.generateCertificate(workId));
    }
}
