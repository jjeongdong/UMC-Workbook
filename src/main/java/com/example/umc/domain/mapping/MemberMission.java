package com.example.umc.domain.mapping;

import com.example.umc.domain.Member;
import com.example.umc.domain.Mission;
import com.example.umc.domain.Store;
import com.example.umc.domain.common.BaseEntity;
import com.example.umc.domain.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'CHALLENGING'")
    private MissionStatus status;

    private LocalDateTime finishedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    public void setStatus(){
        this.status = MissionStatus.COMPLETE;
        this.finishedAt = LocalDateTime.now();
    }
}
