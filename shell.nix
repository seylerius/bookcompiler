{ pkgs ? import <nixpkgs> { } }:

pkgs.mkShell {
  buildInputs = [
    pkgs.leiningen
    # (pkgs.clojure.override { jdk = pkgs.jdk11; })
  ];
}
