package com.zhao.service;

import com.zhao.dao.DeptMapper;
import com.zhao.pojo.Dept;
import com.zhao.vo.DeptVo;
import com.zhao.web.exception.ParamException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @创建人 zhaohuan
 * @邮箱 1101006260@qq.com
 * @创建时间 2018-06-12 23:11
 * @描述
 */
@Service
public class DeptService {
    private DeptMapper mapper;
    private LogService logService;
    private Integer ROOT= 0;

    @Autowired
    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    @Autowired
    public void setMapper(DeptMapper mapper) {
        this.mapper = mapper;
    }

     /*
      *@描述  保存部门信息
      *@创建时间 2018/6/13 10:20
      *@参数 Dept dept
      **/
    public void saveDept(Dept dept){
        dept.setId(null);
        int count = mapper.selectDeptCountBySameLevel(dept.getParentId(), dept.getName(), dept.getId());
        if(count>0){
            throw new ParamException("同一个父级部门下，名称不能重复");
        }
        mapper.insertSelective(dept);
        logService.saveDeptLog(null, Arrays.asList(dept));
    }


     /*
      *@描述  tree展示部门列表
      *@创建时间 2018/6/13 10:19
      *@返回值 List<DeptVo>
      **/
    public List<DeptVo> getDeptList() {
        List<DeptVo> deptVoList = mapper.selectAllDept();
        List<DeptVo> resultList = new ArrayList<>();
        //如果是空
        if(deptVoList==null || deptVoList.size()==0){
            return  resultList;
        }

        //根据seq字段排序
        Collections.sort(deptVoList, new Comparator<DeptVo>() {
            @Override
            public int compare(DeptVo o1, DeptVo o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });

        //查找根节点
        ROOT = 0;
        for(DeptVo vo:deptVoList){
            if(ROOT.equals(vo.getParentId())){
                 resultList.add(vo);
            }
        }
        return findDeptTree(deptVoList,resultList);
    }

    private List<DeptVo> findDeptTree(List<DeptVo> deptVoList,List<DeptVo> resultList) {
        //查找同一层级的部门
        for(int i=0;i<resultList.size();i++){
            DeptVo deptVo = resultList.get(i);
            ROOT = deptVo.getId();
            //todo: 这里可以用这个工具类的，就不用遍历了 org.apache.commons.collections.MultiMap
            for(DeptVo vo:deptVoList){
                if(ROOT.equals(vo.getParentId())){
                    deptVo.getDeptVoList().add(vo);
                }
            }
            //递归查找下级节点
            findDeptTree(deptVoList,deptVo.getDeptVoList());
        }

        return resultList;
    }

    public String getDeptLevel(Integer parentId) {
        String deptLevel = mapper.selectDeptLevel(parentId);
        return  deptLevel==null?"0":deptLevel;
    }

    public void updateDept(Dept dept) {
            //校验:同一个父级部门下，名称不能重复"
            int count = mapper.selectDeptCountBySameLevel(dept.getParentId(), dept.getName(), dept.getId());
            if(count>0){
                throw new ParamException("同一个父级部门下，名称不能重复");
            }

            Dept dbDept = mapper.selectByPrimaryKey(dept.getId());
            Dept parentDept = new Dept();
             boolean isRoot = false;
            if(dept.getParentId().equals(0)){
                //如果是最顶层
                isRoot = true;
                parentDept.setLevel("0");
                parentDept.setParentId(0);
            }else{
                parentDept = mapper.selectByPrimaryKey(dept.getParentId());
            }

            if(!dbDept.getParentId().equals(dept.getParentId())){
                //查询所有下级部门信息
                List<Dept> afterChildDepts = mapper.selectAllChildDept(dbDept.getLevel()+"."+dbDept.getId());
                //更新所有下级部门信息
                if(afterChildDepts.size()>0){
                    List<Dept> beforeChildDepts = new ArrayList<>();
                    for(Dept child:afterChildDepts){
                        Dept before = new Dept();
                        BeanUtils.copyProperties(child,before);
                        beforeChildDepts.add(before);
                        //修改level属性
                        child.setLevel(child.getLevel().replace(dbDept.getLevel(),isRoot?"0":(parentDept.getLevel() + "." + parentDept.getId())));
                    }
                    mapper.batchUpdate(afterChildDepts);
                    //记录日志
                    //logService.saveDeptLog(beforeChildDepts,afterChildDepts);
                }
            }
            //更新当前部门信息
            //之前
            Dept beforeDept = dbDept;
            //之后
            Dept afterDept = dept;
            afterDept.setLevel(isRoot?"0":(parentDept.getLevel() + "." + parentDept.getId()));
            mapper.updateByPrimaryKeySelective(afterDept);
            //记录当前部门日志
            logService.saveDeptLog(Arrays.asList(beforeDept),Arrays.asList(afterDept));
    }


    public void deleteDeptById(Integer deptId) {
        //存在下级部门情况下不能删除
        int childCount =  mapper.selectChildDeptCount(deptId);
        if(childCount>0){
            throw new ParamException("当前部门存在下级部门,不能删除");
        }
        //存在用户的情况下不能删除
        int userCount =  mapper.selectUserCountByDept(deptId);
        if(userCount>0){
            throw new ParamException("当前部门下存在用户,不能删除");
        }
        //删除当前部门
        mapper.deleteByPrimaryKey(deptId);
    }
}