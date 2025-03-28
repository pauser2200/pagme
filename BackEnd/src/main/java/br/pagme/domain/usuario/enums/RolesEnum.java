package br.pagme.domain.usuario.enums;

public enum RolesEnum {


        ADMIN(1L),
        BASIC(2L);

        final long roleId;


    RolesEnum(long roleId) {
            this.roleId = roleId;
        }

        public long getRoleId() {
            return roleId;
        }
}
