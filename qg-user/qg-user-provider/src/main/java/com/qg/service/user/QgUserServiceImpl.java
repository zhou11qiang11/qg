package com.qg.service.user;

import com.qg.exception.LoginException;
import com.qg.mapper.user.QgUserMapper;
import com.qg.pojo.QgUser;
import com.qg.service.QgUserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class QgUserServiceImpl implements QgUserService {
    @Autowired
    private QgUserMapper qgUserMapper;

    @Override
    public QgUser getQgUserById(String id) throws Exception {
        return qgUserMapper.getQgUserById(id);
    }

    @Override
    public List<QgUser> getQgUserListByMap(Map<String, Object> param) throws Exception {
        return qgUserMapper.getQgUserListByMap(param);
    }

    @Override
    public Integer getQgUserCountByMap(Map<String, Object> param) throws Exception {
        return qgUserMapper.getQgUserCountByMap(param);
    }

    @Transactional
    public Integer qdtxAddQgUser(QgUser qgUser) throws Exception {
        return qgUserMapper.qdtxAddQgUser(qgUser);
    }

    @Transactional
    public Integer qdtxModifyQgUser(QgUser qgUser) throws Exception {
        return qgUserMapper.qdtxModifyQgUser(qgUser);
    }

    @Transactional
    public Integer qdtxDeleteQgUserById(String id) throws Exception {
        return qgUserMapper.qdtxDeleteQgUserById(id);
    }

    @Transactional
    public Integer qdtxBatchDeleteQgUser(String ids) throws Exception {
        String[] idsArr = ids.split(",");
        for(String id: idsArr){
            int count = qgUserMapper.qdtxDeleteQgUserById(id);
            if(count<=0){
                throw new RuntimeException();
            }
        }
        return 1;
    }

    @Override
    public QgUser queryQgUserByPhone(String phone) throws Exception {
        QgUser qgUser = qgUserMapper.queryQgUserByPhone(phone);
        return qgUser;
    }
}
