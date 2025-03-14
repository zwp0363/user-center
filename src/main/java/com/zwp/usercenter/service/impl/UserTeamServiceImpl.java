package com.zwp.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwp.usercenter.model.domain.UserTeam;
import com.zwp.usercenter.mapper.UserTeamMapper;
import com.zwp.usercenter.service.UserTeamService;
import org.springframework.stereotype.Service;

/**
* @author zwp
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2025-03-14 14:54:40
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService{

}




