<?php

namespace App\Entity;

use App\Repository\EmploisRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=EmploisRepository::class)
 */
class Emplois
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity=User::class, inversedBy="emplois")
     * @ORM\JoinColumn(nullable=false)
     */
    private $User;

    /**
     * @ORM\Column(type="datetime")
     * @Assert\GreaterThan("today UTC")
     */
    private $Ddebut;

    /**
     * @ORM\Column(type="datetime")
     * @Assert\GreaterThan(propertyPath="Ddebut")
     */
    private $Dfin;
    /**
     * @ORM\Column(type="string", length=10)
     */
    private $Nom;

    /**
     * @ORM\Column(type="string", length=10)
     */
    private $Prenom;

    /**
     * @ORM\Column(type="integer")
     */
    private $CIN;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getUser(): ?User
    {
        return $this->User;
    }

    public function setUser(?User $User): self
    {
        $this->User = $User;

        return $this;
    }

    public function getDdebut(): ?\DateTimeInterface
    {
        return $this->Ddebut;
    }

    public function setDdebut(\DateTimeInterface $Ddebut): self
    {
        $this->Ddebut = $Ddebut;

        return $this;
    }

    public function getDfin(): ?\DateTimeInterface
    {
        return $this->Dfin;
    }

    public function setDfin(\DateTimeInterface $Dfin): self
    {
        $this->Dfin = $Dfin;

        return $this;
    }
    public function getNom(): ?string
    {
        return $this->Nom;
    }

    public function setNom(string $Nom): self
    {
        $this->Nom = $Nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->Prenom;
    }

    public function setPrenom(string $Prenom): self
    {
        $this->Prenom = $Prenom;

        return $this;
    }

    public function getCIN(): ?int
    {
        return $this->CIN;
    }

    public function setCIN(int $CIN): self
    {
        $this->CIN = $CIN;

        return $this;
    }
}
