#!/bin/bash
# 쉬고 있는 profile 찾기 : real1이 사용 중이면 real2가 쉼
function find_idle_profile()
{
  #nginx와 연결되어 있는 스프링 부트가 정상 작동 중인지 확인
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

  if[${RESPONSE_CODE} -ge 400]
  then
    CURRENT_PROFILE=real2
  else
    CURRENT_PROFILE=$(curl -s http://localhost/profile)
  fi

  #연결되지 않은 profile 저장
  if [ ${CURRENT_PROFILE} == real1 ]
  then
    IDLE_PROFILE=real2
  else
    IDLE_PROFILE=real1
  fi

  echo "${IDLE_PROFILE}"
  #idle_profile을 출력한 후 그 값을 캐치하는 방식을 사용
}
function find_idle_port() {
    IDLE_PROFILE=$(find_idle_profile)
    if [ ${IDLE_PROFILE} == real1 ]
    then
      echo "8081"
    else
      echo "8082"
    fi
}
